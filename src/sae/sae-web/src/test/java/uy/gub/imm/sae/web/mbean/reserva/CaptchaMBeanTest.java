package uy.gub.imm.sae.web.mbean.reserva;

import org.junit.jupiter.api.Test;
import uy.gub.imm.sae.exception.UserException;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Tests del helper de captcha y del patrón de carga en los MBeans:
 * - sin fallback test/test
 * - fallo => captchaNoDisponible=true
 * - fallo NO debe setear errorInit (evita página en blanco)
 */
class CaptchaMBeanTest {

    // ------------------------------------------------------------------
    // Helper seleccionarPreguntaCaptcha (BaseMBean)
    // ------------------------------------------------------------------

    @Test
    void givenEmptyCaptchaQuestions_whenSelectingQuestion_thenThrowsUserException() {
        TestBaseMBean mBean = new TestBaseMBean();
        SesionMBeanStub sesion = new SesionMBeanStub(Collections.emptyMap());

        assertThatThrownBy(() -> mBean.seleccionarPreguntaCaptcha(sesion))
                .isInstanceOfSatisfying(UserException.class,
                        ex -> assertThat(ex.getCodigoError()).isEqualTo(BaseMBean.CAPTCHA_NO_DISPONIBLE));
        assertThat(sesion.getPaso3Captcha()).isNull();
    }

    @Test
    void givenNullCaptchaMap_whenSelectingQuestion_thenThrowsUserException() {
        TestBaseMBean mBean = new TestBaseMBean();
        SesionMBeanStub sesion = new SesionMBeanStub(null);

        assertThatThrownBy(() -> mBean.seleccionarPreguntaCaptcha(sesion))
                .isInstanceOfSatisfying(UserException.class,
                        ex -> assertThat(ex.getCodigoError()).isEqualTo(BaseMBean.CAPTCHA_NO_DISPONIBLE));
    }

    @Test
    void givenCaptchaQuestion_whenSelectedThenStoresExpectedAnswer() throws UserException {
        TestBaseMBean mBean = new TestBaseMBean();
        Map<String, String> preguntas = new HashMap<>();
        preguntas.put("Pregunta", "Respuesta");
        SesionMBeanStub sesion = new SesionMBeanStub(preguntas);

        String texto = mBean.seleccionarPreguntaCaptcha(sesion);

        assertThat(texto).isEqualTo("Pregunta");
        assertThat(sesion.getPaso3Captcha()).isEqualTo("Respuesta");
    }

    @Test
    void givenNullAnswer_whenSelectingQuestion_thenThrowsUserException() {
        TestBaseMBean mBean = new TestBaseMBean();
        Map<String, String> preguntas = new HashMap<>();
        preguntas.put("Pregunta", null);
        SesionMBeanStub sesion = new SesionMBeanStub(preguntas);

        assertThatThrownBy(() -> mBean.seleccionarPreguntaCaptcha(sesion))
                .isInstanceOfSatisfying(UserException.class,
                        ex -> assertThat(ex.getCodigoError()).isEqualTo(BaseMBean.CAPTCHA_NO_DISPONIBLE));
        assertThat(sesion.getPaso3Captcha()).isNull();
    }

    @Test
    void givenEmptyAnswer_whenSelectingQuestion_thenThrowsUserException() {
        TestBaseMBean mBean = new TestBaseMBean();
        Map<String, String> preguntas = new HashMap<>();
        preguntas.put("Pregunta", "   ");
        SesionMBeanStub sesion = new SesionMBeanStub(preguntas);

        assertThatThrownBy(() -> mBean.seleccionarPreguntaCaptcha(sesion))
                .isInstanceOfSatisfying(UserException.class,
                        ex -> assertThat(ex.getCodigoError()).isEqualTo(BaseMBean.CAPTCHA_NO_DISPONIBLE));
        assertThat(sesion.getPaso3Captcha()).isNull();
    }

    @Test
    void givenMultipleQuestions_whenSelecting_thenPicksOneValidQuestionAndAnswer() throws UserException {
        TestBaseMBean mBean = new TestBaseMBean();
        Map<String, String> preguntas = new HashMap<>();
        preguntas.put("P1", "R1");
        preguntas.put("P2", "R2");
        SesionMBeanStub sesion = new SesionMBeanStub(preguntas);

        String texto = mBean.seleccionarPreguntaCaptcha(sesion);

        assertThat(texto).isIn("P1", "P2");
        assertThat(sesion.getPaso3Captcha()).isEqualTo(preguntas.get(texto));
    }

    // ------------------------------------------------------------------
    // Paso2MBean.cargarCaptcha — patrón JSF (sin errorInit)
    // ------------------------------------------------------------------

    @Test
    void givenCaptchaAvailable_whenPaso2LoadsCaptcha_thenQuestionReadyAndErrorInitFalse() {
        Map<String, String> preguntas = new HashMap<>();
        preguntas.put("¿Color del cielo?", "azul");
        SesionMBeanStub sesion = new SesionMBeanStub(preguntas);

        Paso2MBean mBean = new Paso2MBean();
        mBean.setSesionMBean(sesion);
        mBean.cargarCaptcha();

        assertThat(mBean.getTextoIndicativoCaptcha()).isEqualTo("¿Color del cielo?");
        assertThat(mBean.isCaptchaNoDisponible()).isFalse();
        assertThat(mBean.getErrorInit()).isFalse();
        assertThat(sesion.getPaso3Captcha()).isEqualTo("azul");
    }

    @Test
    void givenNoCaptcha_whenPaso2LoadsCaptcha_thenMarksUnavailableWithoutErrorInit() {
        SesionMBeanStub sesion = new SesionMBeanStub(Collections.emptyMap());
        // residual answer must be cleared on failure
        sesion.setPaso3Captcha("vieja");

        Paso2MBean mBean = new Paso2MBean();
        mBean.setSesionMBean(sesion);
        mBean.cargarCaptcha();

        assertThat(mBean.getTextoIndicativoCaptcha()).isEmpty();
        assertThat(mBean.isCaptchaNoDisponible()).isTrue();
        assertThat(mBean.getErrorInit()).isFalse();
        assertThat(sesion.getPaso3Captcha()).isNull();
    }

    // ------------------------------------------------------------------
    // ModificarPaso3MBean.cargarCaptcha
    // ------------------------------------------------------------------

    @Test
    void givenCaptchaAvailable_whenModificarPaso3LoadsCaptcha_thenQuestionReadyAndErrorInitFalse() {
        Map<String, String> preguntas = new HashMap<>();
        preguntas.put("Capital", "Montevideo");
        SesionMBeanStub sesion = new SesionMBeanStub(preguntas);

        ModificarPaso3MBean mBean = new ModificarPaso3MBean();
        mBean.setSesionMBean(sesion);
        mBean.cargarCaptcha();

        assertThat(mBean.getTextoIndicativoCaptcha()).isEqualTo("Capital");
        assertThat(mBean.isCaptchaNoDisponible()).isFalse();
        assertThat(mBean.getErrorInit()).isFalse();
        assertThat(sesion.getPaso3Captcha()).isEqualTo("Montevideo");
    }

    @Test
    void givenNoCaptcha_whenModificarPaso3LoadsCaptcha_thenMarksUnavailableWithoutErrorInit() {
        SesionMBeanStub sesion = new SesionMBeanStub(Collections.emptyMap());

        ModificarPaso3MBean mBean = new ModificarPaso3MBean();
        mBean.setSesionMBean(sesion);
        mBean.cargarCaptcha();

        assertThat(mBean.getTextoIndicativoCaptcha()).isEmpty();
        assertThat(mBean.isCaptchaNoDisponible()).isTrue();
        assertThat(mBean.getErrorInit()).isFalse();
        assertThat(sesion.getPaso3Captcha()).isNull();
    }

    // ------------------------------------------------------------------
    // MultiplePasoFinalMBean.cargarCaptcha
    // ------------------------------------------------------------------

    @Test
    void givenCaptchaAvailable_whenMultiplePasoFinalLoadsCaptcha_thenQuestionReadyAndErrorInitFalse() {
        Map<String, String> preguntas = new HashMap<>();
        preguntas.put("2+2", "4");
        SesionMBeanStub sesion = new SesionMBeanStub(preguntas);

        MultiplePasoFinalMBean mBean = new MultiplePasoFinalMBean();
        mBean.setSesionMBean(sesion);
        mBean.cargarCaptcha();

        assertThat(mBean.getTextoIndicativoCaptcha()).isEqualTo("2+2");
        assertThat(mBean.isCaptchaNoDisponible()).isFalse();
        assertThat(mBean.isErrorInit()).isFalse();
        assertThat(sesion.getPaso3Captcha()).isEqualTo("4");
    }

    @Test
    void givenNoCaptcha_whenMultiplePasoFinalLoadsCaptcha_thenMarksUnavailableWithoutErrorInit() {
        SesionMBeanStub sesion = new SesionMBeanStub(Collections.emptyMap());

        MultiplePasoFinalMBean mBean = new MultiplePasoFinalMBean();
        mBean.setSesionMBean(sesion);
        mBean.cargarCaptcha();

        assertThat(mBean.getTextoIndicativoCaptcha()).isEmpty();
        assertThat(mBean.isCaptchaNoDisponible()).isTrue();
        assertThat(mBean.isErrorInit()).isFalse();
        assertThat(sesion.getPaso3Captcha()).isNull();
    }

    @Test
    void givenPreviousFailure_whenCaptchaBecomesAvailable_thenClearsUnavailableFlag() {
        Paso2MBean mBean = new Paso2MBean();

        SesionMBeanStub empty = new SesionMBeanStub(Collections.emptyMap());
        mBean.setSesionMBean(empty);
        mBean.cargarCaptcha();
        assertThat(mBean.isCaptchaNoDisponible()).isTrue();

        Map<String, String> preguntas = new HashMap<>();
        preguntas.put("OK", "si");
        SesionMBeanStub ok = new SesionMBeanStub(preguntas);
        mBean.setSesionMBean(ok);
        mBean.cargarCaptcha();

        assertThat(mBean.isCaptchaNoDisponible()).isFalse();
        assertThat(mBean.getTextoIndicativoCaptcha()).isEqualTo("OK");
        assertThat(mBean.getErrorInit()).isFalse();
        assertThat(ok.getPaso3Captcha()).isEqualTo("si");
    }

    private static class TestBaseMBean extends BaseMBean {
    }

    private static class SesionMBeanStub extends SesionMBean {
        private final Map<String, String> preguntasCaptcha;
        private String paso3Captcha;

        private SesionMBeanStub(Map<String, String> preguntasCaptcha) {
            this.preguntasCaptcha = preguntasCaptcha;
        }

        @Override
        public Map<String, String> getPreguntasCaptcha() {
            return preguntasCaptcha;
        }

        @Override
        public String getPaso3Captcha() {
            return paso3Captcha;
        }

        @Override
        public void setPaso3Captcha(String paso3Captcha) {
            this.paso3Captcha = paso3Captcha;
        }
    }
}

/**
 * Esta función es llamada al renderizar el calendario para pintar un día 
 * del mes de acuerdo a las disponibilidades
 * @param date
 * @returns [0]=si el día sea seleccionable o no (true/false)
 *          [1]=la clase CSS a utilizar para el día
 *          [2]=el tooltip a utilizar para el día
 */
function disponibilidadFecha(date) {
	if(document.getElementById("form:fechasdisp")!=null) {
		var date_array = document.getElementById("form:fechasdisp").value;
		var day   = date.getDate();
		var month = date.getMonth()+1;
		var year  = date.getFullYear();
		var value =  day+ "/" +month+"/"+year;
		var split1 = date_array.split("[");
		var split2 = split1[1].split("]");
		var split3 = split2[0].split(",");
		var result = false;
		for (var i = 0; i < split3.length; i++) {
			var element = split3[i];
			if (element.indexOf(value)==1) {
				result = true;
				break;
			}
		}
	}
	var today = new Date();
	var hoy = today.getDate();
	var mesActual = today.getMonth()+1; //January is 0!
	var anioActual = today.getFullYear();
	
	if ( year < anioActual) {
		return [result, "fechaPasada"];
	}else if ( year > anioActual) {
		return [result, "fechaFutura"];
	}else {
		if(month < mesActual) {
			return [result, "fechaPasada"];
		}else if(month > mesActual) {
			return [result, "fechaFutura"];
		}else	{
			if(day<hoy) {
				return [result, "fechaPasada"];
			}else if(day>hoy) {
				return [result, "fechaFutura"];
			}else {
				return [result, "fechaHoy"];
			} 
		}
	}
};

$(document).on('focus', '.datepicker',  function() {
  var formatoFecha = document.getElementById('formatoFecha').value;
  $(this).datepicker({
    dateFormat: formatoFecha
  });
});

$(document).on('keydown', '.datepicker',    function() {
//Por alguna razón da error al invocar esta función, pero no es necesaria ya que solo se admite la tecla tab
//    $.datepicker.customKeyPress(event);
});

function soloTabs(e){
  //9 es tab, 0 es otro caracter de control (Firefox y SeaMonkey devuelven 0)
  var keynum = e.which;
  return (keynum==0 || keynum==9);
}

document.addEventListener("DOMContentLoaded", function(event) {

PrimeFaces.locales['es'] = {
        closeText: 'Cerrar',
        prevText: 'Anterior',
        nextText: 'Siguiente',
        monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
        monthNamesShort: ['Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun', 'Jul', 'Ago', 'Sep', 'Oct', 'Nov', 'Dic'],
        dayNames: ['Domingo', 'Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado'],
        dayNamesShort: ['Dom', 'Lun', 'Mar', 'Mie', 'Jue', 'Vie', 'Sab'],
        dayNamesMin: ['D', 'L', 'M', 'X', 'J', 'V', 'S'],
        weekHeader: 'Semana',
        firstDay: 1,
        isRTL: false,
        showMonthAfterYear: false,
        yearSuffix: '',
        timeOnlyTitle: 'Sólo hora',
        timeText: 'Tiempo',
        hourText: 'Hora',
        minuteText: 'Minuto',
        secondText: 'Segundo',
        millisecondText: 'Milisegundo',
        currentText: 'Fecha actual',
        ampm: false,
        month: 'Mes',
        week: 'Semana',
        day: 'Día',
        allDayText: 'Todo el día',
        today: 'Hoy',
        clear: 'Claro'
    };
});

 function addSpecialClassToDisabledDates() {
        $(".ui-state-default").addClass("disable-dates");
    }

// Manejar selección visual de turnos usando delegación de eventos
(function() {
    // Usar delegación de eventos en el document para que funcione incluso después de AJAX updates
    document.addEventListener('click', function(event) {
        // Verificar si el click fue en un botón de turno o dentro de él
        var target = event.target;

        // Buscar el botón padre si se hizo click en un elemento interno
        while (target && target !== document) {
            if (target.classList && target.classList.contains('btn-turno')) {
                // Remover clase de todos los botones
                var botones = document.querySelectorAll('#primeros-turnos .btn-turno');
                botones.forEach(function(btn) {
                    btn.classList.remove('turno-seleccionado');
                });

                // Agregar clase al botón clickeado
                target.classList.add('turno-seleccionado');
                break;
            }
            target = target.parentElement;
        }
    });
})();
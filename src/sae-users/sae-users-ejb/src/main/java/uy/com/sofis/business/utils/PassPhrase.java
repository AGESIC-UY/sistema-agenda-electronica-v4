/*
 *  Nombre del cliente
 *  Sistema de Gestión
 *  Desarrollado por Sofis Solutions
 */

package uy.com.sofis.business.utils;

/**
 *
 * @author usuario
 */
public class PassPhrase {

  /** The random number generator. */
  protected static java.util.Random r = new java.util.Random();

  /*
   * Set of characters that is valid. Must be printable, memorable, and "won't
   * break HTML" (i.e., not ' <', '>', '&', '=', ...). or break shell commands
   * (i.e., not ' <', '>', '$', '!', ...). I, L and O are good to leave out,
   * as are numeric zero and one.
   */
  protected static char[] goodChar = { 'a', 'b', 'c', 'd', 'e', 'f', 'g',
      'h', 'j', 'k', 'm', 'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
      'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K',
      'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
      '2', '3', '4', '5', '6', '7', '8', '9', '+', '-', '@', };

  /**
   * Permite generar un password aleatorio de un determinado tamaño
   * @param largo tamaño deseado para el password generado
   * @return devuelve el password generado
   */
  public static String getNext(int largo) {
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < largo; i++) {
      sb.append(goodChar[r.nextInt(goodChar.length)]);
    }
    return sb.toString();
  }

 
}
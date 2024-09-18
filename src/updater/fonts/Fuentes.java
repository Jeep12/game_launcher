package updater.fonts;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.InputStream;
import java.io.IOException;

public class Fuentes {
    private Font font = null;

    // Ruta de las fuentes
    public String RIO = "/updater/fonts/49_la2font.TTF";  
    public String BPR = "/updater/fonts/BPreplay.otf";
    public String ITROMATIC = "/updater/fonts/ltromatic.ttf";

    /**
     * Método para cargar una fuente desde un archivo
     * @param fontName nombre del archivo de fuente
     * @param estilo estilo de la fuente (PLAIN, BOLD, ITALIC)
     * @param tamanio tamaño de la fuente
     * @return la fuente cargada o una fuente por defecto si falla
     */
    public Font fuente(String fontName, int estilo, float tamanio) {
        try {
            // Intenta cargar la fuente desde los recursos
            InputStream is = getClass().getResourceAsStream(fontName);
            System.out.println("updater.fonts.Fuentes.fuente()");
            if (is == null) {
                throw new IOException("No se encontró el archivo de fuente: " + fontName);
            }

            // Carga la fuente como TrueType
            font = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException | IOException ex) {
            // Si hay un problema, muestra un mensaje de error y usa Arial como fallback
            System.err.println("Error cargando la fuente " + fontName + ": " + ex.getMessage());
            font = new Font("Arial", Font.PLAIN, 14);  // Fallback
        }

        // Devuelve la fuente con el estilo y tamaño deseados
        return font.deriveFont(estilo, tamanio);
    }
}

package junittitest;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Template {
    
    private Map<String, String> muuttujat;
    String arvo;
    String teksti;
    
    public Template(String teksti) {
        this.teksti = teksti;
        this.muuttujat = new HashMap<String, String>();
    }
    
    public void korvaa(String muuttuja, String arvo) {
        this.muuttujat.put(muuttuja, arvo);
    }
    
    public String evaluoi() {
        String tulos = teksti;
        for (Entry<String, String> pari : muuttujat.entrySet()) {
            String korvattava = "\\$\\{" + pari.getKey() + "\\}";
            tulos = tulos.replaceAll(korvattava, pari.getValue());
        }
        // tarkistus
        tarkastaPuuttuvat(tulos);
        return tulos;
    }
    
    private void tarkastaPuuttuvat(String teksti) {
        Matcher m = Pattern.compile("\\$\\{.+\\}").matcher(teksti);
        if (m.find()) {
            throw new PuuttuvaArvoException("Muuttujalla " + m.group() +
                    " ei ole arvoa.");
        }
    }
}

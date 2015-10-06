import junittitest.Template;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import junittitest.PuuttuvaArvoException;

public class TemplateTest {
    /*@Test
    public void yksiMuuttuja(){
        Template template = new Template("Terve, ${nimi}");
        template.korvaa("nimi", "Pyyyyryyy");
        assertEquals("Terve, Pyyyyryyy", template.evaluoi());
    }
    
    @Test
    public void uusiArvo() {
        Template temp = new Template("Terve, ${nimi}");
        temp.korvaa("nimi", "Makkipakki");
        assertEquals("Terve, Makkipakki", temp.evaluoi());
    }
    
    @Test
    public void uusiTemplate() {
        Template temp = new Template("Heissulivei, ${nimi}");
        temp.korvaa("nimi", "Maxim");
        assertEquals("Heissulivei, Maxim", temp.evaluoi());
    }
    
    @Test
    public void montaMuuttujaa() {
        Template temp = new Template("${yy}, ${kaa}, ${koo}");
        temp.korvaa("yy", "1");
        temp.korvaa("kaa", "2");
        temp.korvaa("koo", "3");
        assertEquals("1, 2, 3", temp.evaluoi());
    }
    
    @Test
    public void tuntematonMuuttuja() {
        Template temp = new Template("Terve, ${nimi} ${sukunimi}");
        temp.korvaa("nimi", "Ville");
        temp.korvaa("olematon", "Tätä ei käytetä");
        assertEquals("Terve, Ville ${sukunimi}", temp.evaluoi());
    }*/
    
    private Template temp;
    
    @Before
    public void setup() {
        temp = new Template("${yy}, ${kaa}, ${koo}, ${nee}");
        temp.korvaa("yy", "1");
        temp.korvaa("kaa", "2");
        temp.korvaa("koo", "3");
        }
    @Test (expected = PuuttuvaArvoException.class)
    public void montaMuuttujaa() {
    assertEquals("1, 2, 3, ${nee}", temp.evaluoi());
    }

    @Test
    public void tuntematonMuuttuja() {
    temp.korvaa("nee", "4");
    temp.korvaa("olematon", "Tätä ei käytetä");
    assertEquals("1, 2, 3, 4", temp.evaluoi());
    } 
    
    @Test
    public void puuttuvastaPoikkeus() {
    try {
        new Template("Terve, ${olematon}").evaluoi();
        fail("evaluoi()-metodin tulisi heittää poikkeus, jos "
        + "muuttujalle ei ole asetettu arvoa.");
    } catch (PuuttuvaArvoException expected) {
        assertEquals("Muuttujalla ${olematon} ei ole arvoa.",expected.getMessage());
    } 
    } 
}

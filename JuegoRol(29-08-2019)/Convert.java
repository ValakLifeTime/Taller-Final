import java.io.File;
import java.util.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;
import greenfoot.*;
public class Convert  
{
private Koprulu savedWorld;
private final String defaultSavePath = "Save.xml";
public Convert(Koprulu mundo)
{
    savedWorld = mundo;
}
public Convert(String guardado) throws Exception
{
    savedWorld = parseSaveFile(guardado);
}

public void SaveWorld() 
{
    
   ArrayList<Actor> worldActors = new ArrayList<>(savedWorld.getObjects(Actor.class));
   ArrayList<Extraterrestre> extraterrestres = new ArrayList<>();
   ArrayList<GasDeposit> gasDeposit = new ArrayList<>();
   ArrayList<Deposito> deposito = new ArrayList<>();
   ArrayList<Comando> comando = new ArrayList<>();
   ArrayList<HealthCenter> healthCenter = new ArrayList<>();
   ArrayList<Obstaculo> obstaculo = new ArrayList<>();
   int worldIterations = savedWorld.getIteraciones();
     for (Actor actor : worldActors) {

            if (actor instanceof Extraterrestre) {
                extraterrestres.add((Extraterrestre) actor);
            } else if (actor instanceof GasDeposit) {
                gasDeposit.add((GasDeposit) actor);
            }
            else if (actor instanceof Deposito) {
                deposito.add((Deposito) actor);
            }
            else if (actor instanceof Comando) {
                comando.add((Comando) actor);
            }
            else if (actor instanceof HealthCenter) {
                healthCenter.add((HealthCenter) actor);
            }
            else if (actor instanceof Obstaculo) {
                obstaculo.add((Obstaculo) actor);
            }
        }
    try {
        
        

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // Base
            Document doc = docBuilder.newDocument();
            Element root = doc.createElement("Actores");
            doc.appendChild(root);
            //Extraterrestres
            Element elementoExtraterrestres = doc.createElement("Extraterrestres");
            root.appendChild(elementoExtraterrestres);
            
            Element elementoProtoss = doc.createElement("Protoss");
            elementoExtraterrestres.appendChild(elementoProtoss);
            
            Element elementoTerran = doc.createElement("Terran");
            elementoExtraterrestres.appendChild(elementoTerran);
            
            Element elementoZerg = doc.createElement("Zerg");
            elementoExtraterrestres.appendChild(elementoZerg);
            
            for (Extraterrestre extraterrestre : extraterrestres) {
                if (extraterrestre instanceof Protoss) {

                    String extraterrestreString = "protoss" + extraterrestre.hashCode();
                    Element protoss = doc.createElement(extraterrestreString);

                    protoss.setAttribute("X", String.valueOf(extraterrestre.getX()));
                    protoss.setAttribute("Y", String.valueOf(extraterrestre.getY()));

                    protoss.setAttribute("energia", String.valueOf(extraterrestre.getEnergia()));
                    protoss.setAttribute("estadoDeVida", String.valueOf(extraterrestre.getLifeState()));
                    protoss.setAttribute("estadoDeMovimiento", String.valueOf(extraterrestre.getControlState()));
                    protoss.setAttribute("gas", String.valueOf(extraterrestre.getGas()));
                    protoss.setAttribute("cristales", String.valueOf(extraterrestre.getCristales()));
                    protoss.setAttribute("contador", String.valueOf(extraterrestre.getcontRecursos()));
                    protoss.setAttribute("capacidadCuracion", String.valueOf(extraterrestre.getCapacidadCuracion()));
                    protoss.setAttribute("rol", String.valueOf(extraterrestre.getRol()));       
                    protoss.setAttribute("contadorenergia", String.valueOf(extraterrestre.getContador())); 
                }
                else if (extraterrestre instanceof Terran) {

                    String extraterrestreString = "protoss" + extraterrestre.hashCode();
                    Element terran = doc.createElement(extraterrestreString);

                    terran.setAttribute("X", String.valueOf(extraterrestre.getX()));
                    terran.setAttribute("Y", String.valueOf(extraterrestre.getY()));

                    terran.setAttribute("energia", String.valueOf(extraterrestre.getEnergia()));
                    terran.setAttribute("estadoDeVida", String.valueOf(extraterrestre.getLifeState()));
                    terran.setAttribute("estadoDeMovimiento", String.valueOf(extraterrestre.getControlState()));
                    terran.setAttribute("gas", String.valueOf(extraterrestre.getGas()));
                    terran.setAttribute("cristales", String.valueOf(extraterrestre.getCristales()));
                    terran.setAttribute("contador", String.valueOf(extraterrestre.getcontRecursos()));
                    terran.setAttribute("capacidadCuracion", String.valueOf(extraterrestre.getCapacidadCuracion()));
                    terran.setAttribute("rol", String.valueOf(extraterrestre.getRol()));  
                    terran.setAttribute("contadorenergia", String.valueOf(extraterrestre.getContador()));
                }
                else if (extraterrestre instanceof Zerg) {

                    String extraterrestreString = "protoss" + extraterrestre.hashCode();
                    Element zerg = doc.createElement(extraterrestreString);

                    zerg.setAttribute("X", String.valueOf(extraterrestre.getX()));
                    zerg.setAttribute("Y", String.valueOf(extraterrestre.getY()));

                    zerg.setAttribute("energia", String.valueOf(extraterrestre.getEnergia()));
                    zerg.setAttribute("estadoDeVida", String.valueOf(extraterrestre.getLifeState()));
                    zerg.setAttribute("estadoDeMovimiento", String.valueOf(extraterrestre.getControlState()));
                    zerg.setAttribute("gas", String.valueOf(extraterrestre.getGas()));
                    zerg.setAttribute("cristales", String.valueOf(extraterrestre.getCristales()));
                    zerg.setAttribute("contador", String.valueOf(extraterrestre.getcontRecursos()));
                    zerg.setAttribute("capacidadCuracion", String.valueOf(extraterrestre.getCapacidadCuracion()));
                    zerg.setAttribute("rol", String.valueOf(extraterrestre.getRol())); 
                    zerg.setAttribute("contadorenergia", String.valueOf(extraterrestre.getContador()));
                }
                
            }
            Element elementoGasDeposit = doc.createElement("GasDeposits");
            Element elementoDeposito = doc.createElement("Deposito");
            Element elementoComando = doc.createElement("Comando");
            Element elementoHealthCenter = doc.createElement("HealthCenter");
            Element elementoObstaculo = doc.createElement("Obstaculo");
            for (GasDeposit g : gasDeposit) {

                String elementoString =
                        g.getClass().toString().toLowerCase().replace("class ", "")
                                + g.hashCode();
                Element temp = doc.createElement(elementoString);
                temp.setAttribute("X", String.valueOf(g.getX()));
                temp.setAttribute("Y", String.valueOf(g.getY()));
            }
            for (Deposito d : deposito) {

                String elementoString =
                        d.getClass().toString().toLowerCase().replace("class ", "")
                                + d.hashCode();
                Element temp = doc.createElement(elementoString);
                temp.setAttribute("X", String.valueOf(d.getX()));
                temp.setAttribute("Y", String.valueOf(d.getY()));
                
                temp.setAttribute("cantMaxima", String.valueOf(d.getCantMax()));
            }
            for (Comando co : comando) {

                String elementoString =
                        co.getClass().toString().toLowerCase().replace("class ", "")
                                + co.hashCode();
                Element temp = doc.createElement(elementoString);
                temp.setAttribute("X", String.valueOf(co.getX()));
                temp.setAttribute("Y", String.valueOf(co.getY()));
                
                temp.setAttribute("Nombre", String.valueOf(co.getNombre()));
                temp.setAttribute("Barra", String.valueOf(co.getBar()));
            }
            for (HealthCenter h : healthCenter) {

                String elementoString =
                        h.getClass().toString().toLowerCase().replace("class ", "")
                                + h.hashCode();
                Element temp = doc.createElement(elementoString);
                temp.setAttribute("X", String.valueOf(h.getX()));
                temp.setAttribute("Y", String.valueOf(h.getY()));
                
                temp.setAttribute("Celdas", String.valueOf(h.getCeldaS()));
                temp.setAttribute("Estado", String.valueOf(h.getEstado()));
            }
            for (Obstaculo o : obstaculo) {

                String elementoString =
                        o.getClass().toString().toLowerCase().replace("class ", "")
                                + o.hashCode();
                Element temp = doc.createElement(elementoString);
                temp.setAttribute("X", String.valueOf(o.getX()));
                temp.setAttribute("Y", String.valueOf(o.getY()));
            }
             Element elementoMundo = doc.createElement("Mundo");
            elementoMundo.setAttribute("iteraciones", String.valueOf(savedWorld.getIteraciones()));
            elementoMundo.setAttribute("p1Raza", String.valueOf(savedWorld.getp1Raza()));
            elementoMundo.setAttribute("p2Raza", String.valueOf(savedWorld.getp2Raza()));
            root.appendChild(elementoMundo);
            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(defaultSavePath));

            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);

            transformer.transform(source, result);
}
catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
}
public static Koprulu parseSaveFile(String filePath) throws Exception {

        Koprulu loadedWorld = null;

        try {

            File saveFile = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(saveFile);

            doc.getDocumentElement().normalize();

            Node mundo = doc.getElementsByTagName("Mundo").item(0);
            Element infoMundo = (Element) mundo;
            int iteracionesMundo = Integer.parseInt(infoMundo.getAttribute("iteraciones"));
            String p1Raza = (infoMundo.getAttribute("p1Raza"));
            String p2Raza = (infoMundo.getAttribute("p2Raza"));
            loadedWorld = new Koprulu(p1Raza,p2Raza,iteracionesMundo, true);
            
            Element extraterrestres = (Element) doc.getElementsByTagName("Extraterrestres").item(0);
            if(p1Raza.equals("Protoss"))
            {
            NodeList protossNode = extraterrestres.getElementsByTagName("Protoss").item(0).getChildNodes();
            for(int i=0; i< protossNode.getLength();i++)
            {
             Element protoss = (Element) protossNode.item(i);

                int x = Integer.parseInt(protoss.getAttribute("X"));
                int y = Integer.parseInt(protoss.getAttribute("Y"));

                boolean lifeState = Boolean.parseBoolean(protoss.getAttribute("estadoDeVida"));
                boolean controlState = Boolean.parseBoolean(protoss.getAttribute("estadoDeMovimiento"));
                int energia = Integer.parseInt(protoss.getAttribute("energia"));
                int gas = Integer.parseInt(protoss.getAttribute("gas"));
                int cristales = Integer.parseInt(protoss.getAttribute("cristales"));
                int capacidadCuracion = Integer.parseInt(protoss.getAttribute("energia"));
                String rol = protoss.getAttribute("rol");

                Protoss temp = new Protoss(rol,controlState,lifeState,cristales, gas, energia, capacidadCuracion);
                loadedWorld.addObject(temp, x, y);   
            } 
            if(p2Raza.equals("Terran"))
            {
              NodeList terranNode = extraterrestres.getElementsByTagName("Terran").item(0).getChildNodes();
            for(int i=0; i< terranNode.getLength();i++)
            {
             Element terran = (Element) terranNode.item(i);

                int x = Integer.parseInt(terran.getAttribute("X"));
                int y = Integer.parseInt(terran.getAttribute("Y"));

                boolean lifeState = Boolean.parseBoolean(terran.getAttribute("estadoDeVida"));
                boolean controlState = Boolean.parseBoolean(terran.getAttribute("estadoDeMovimiento"));
                int energia = Integer.parseInt(terran.getAttribute("energia"));
                int gas = Integer.parseInt(terran.getAttribute("gas"));
                int cristales = Integer.parseInt(terran.getAttribute("cristales"));
                int capacidadCuracion = Integer.parseInt(terran.getAttribute("energia"));
                String rol = terran.getAttribute("rol");

                Terran temp = new Terran(rol,controlState, lifeState,cristales, gas, energia, capacidadCuracion);
                loadedWorld.addObject(temp, x, y);   
            }
        }
            else{
                NodeList zergNode = extraterrestres.getElementsByTagName("Zerg").item(0).getChildNodes();
            for(int i=0; i< zergNode.getLength();i++)
            {
             Element zerg = (Element) zergNode.item(i);

                int x = Integer.parseInt(zerg.getAttribute("X"));
                int y = Integer.parseInt(zerg.getAttribute("Y"));

                boolean lifeState = Boolean.parseBoolean(zerg.getAttribute("estadoDeVida"));
                boolean controlState = Boolean.parseBoolean(zerg.getAttribute("estadoDeMovimiento"));
                int energia = Integer.parseInt(zerg.getAttribute("energia"));
                int gas = Integer.parseInt(zerg.getAttribute("gas"));
                int cristales = Integer.parseInt(zerg.getAttribute("cristales"));
                int capacidadCuracion = Integer.parseInt(zerg.getAttribute("energia"));
                String rol = zerg.getAttribute("rol");

                Zerg temp = new Zerg(rol,controlState, lifeState,cristales, gas, energia, capacidadCuracion);
                loadedWorld.addObject(temp, x, y);   
            }
            }
               
        }
            else if (p1Raza.equals("Terran"))
            {
            NodeList terranNode = extraterrestres.getElementsByTagName("Terran").item(0).getChildNodes();
            for(int i=0; i< terranNode.getLength();i++)
            {
             Element terran = (Element) terranNode.item(i);

                int x = Integer.parseInt(terran.getAttribute("X"));
                int y = Integer.parseInt(terran.getAttribute("Y"));

                boolean lifeState = Boolean.parseBoolean(terran.getAttribute("estadoDeVida"));
                boolean controlState = Boolean.parseBoolean(terran.getAttribute("estadoDeMovimiento"));
                int energia = Integer.parseInt(terran.getAttribute("energia"));
                int gas = Integer.parseInt(terran.getAttribute("gas"));
                int cristales = Integer.parseInt(terran.getAttribute("cristales"));
                int capacidadCuracion = Integer.parseInt(terran.getAttribute("energia"));
                String rol = terran.getAttribute("rol");

                Terran temp = new Terran(rol,controlState, lifeState,cristales, gas, energia, capacidadCuracion);
                loadedWorld.addObject(temp, x, y);   
            }
            if(p2Raza.equals("Protoss"))
            {
              NodeList protossNode = extraterrestres.getElementsByTagName("Protoss").item(0).getChildNodes();
            for(int i=0; i< protossNode.getLength();i++)
            {
             Element protoss = (Element) protossNode.item(i);

                int x = Integer.parseInt(protoss.getAttribute("X"));
                int y = Integer.parseInt(protoss.getAttribute("Y"));

                boolean lifeState = Boolean.parseBoolean(protoss.getAttribute("estadoDeVida"));
                boolean controlState = Boolean.parseBoolean(protoss.getAttribute("estadoDeMovimiento"));
                int energia = Integer.parseInt(protoss.getAttribute("energia"));
                int gas = Integer.parseInt(protoss.getAttribute("gas"));
                int cristales = Integer.parseInt(protoss.getAttribute("cristales"));
                int capacidadCuracion = Integer.parseInt(protoss.getAttribute("energia"));
                String rol = protoss.getAttribute("rol");

                Protoss temp = new Protoss(rol,controlState, lifeState,cristales, gas, energia, capacidadCuracion);
                loadedWorld.addObject(temp, x, y);   
            }  
            }
            else{
                NodeList zergNode = extraterrestres.getElementsByTagName("Zerg").item(0).getChildNodes();
            for(int i=0; i< zergNode.getLength();i++)
            {
             Element zerg = (Element) zergNode.item(i);

                int x = Integer.parseInt(zerg.getAttribute("X"));
                int y = Integer.parseInt(zerg.getAttribute("Y"));

                boolean lifeState = Boolean.parseBoolean(zerg.getAttribute("estadoDeVida"));
                boolean controlState = Boolean.parseBoolean(zerg.getAttribute("estadoDeMovimiento"));
                int energia = Integer.parseInt(zerg.getAttribute("energia"));
                int gas = Integer.parseInt(zerg.getAttribute("gas"));
                int cristales = Integer.parseInt(zerg.getAttribute("cristales"));
                int capacidadCuracion = Integer.parseInt(zerg.getAttribute("energia"));
                String rol = zerg.getAttribute("rol");

                Zerg temp = new Zerg(rol,controlState, lifeState,cristales, gas, energia, capacidadCuracion);
                loadedWorld.addObject(temp, x, y);   
            }
            }
        }
        
        else if (p1Raza.equals("Zerg"))
            {
            NodeList zergNode = extraterrestres.getElementsByTagName("Zerg").item(0).getChildNodes();
            for(int i=0; i< zergNode.getLength();i++)
            {
             Element zerg = (Element) zergNode.item(i);

                int x = Integer.parseInt(zerg.getAttribute("X"));
                int y = Integer.parseInt(zerg.getAttribute("Y"));

                boolean lifeState = Boolean.parseBoolean(zerg.getAttribute("estadoDeVida"));
                boolean controlState = Boolean.parseBoolean(zerg.getAttribute("estadoDeMovimiento"));
                int energia = Integer.parseInt(zerg.getAttribute("energia"));
                int gas = Integer.parseInt(zerg.getAttribute("gas"));
                int cristales = Integer.parseInt(zerg.getAttribute("cristales"));
                int capacidadCuracion = Integer.parseInt(zerg.getAttribute("energia"));
                String rol = zerg.getAttribute("rol");

                Zerg temp = new Zerg(rol,controlState, lifeState,cristales, gas, energia, capacidadCuracion);
                loadedWorld.addObject(temp, x, y);   
            }
            if(p2Raza.equals("Protoss"))
            {
              NodeList protossNode = extraterrestres.getElementsByTagName("Protoss").item(0).getChildNodes();
            for(int i=0; i< protossNode.getLength();i++)
            {
             Element protoss = (Element) protossNode.item(i);

                int x = Integer.parseInt(protoss.getAttribute("X"));
                int y = Integer.parseInt(protoss.getAttribute("Y"));

                boolean lifeState = Boolean.parseBoolean(protoss.getAttribute("estadoDeVida"));
                boolean controlState = Boolean.parseBoolean(protoss.getAttribute("estadoDeMovimiento"));
                int energia = Integer.parseInt(protoss.getAttribute("energia"));
                int gas = Integer.parseInt(protoss.getAttribute("gas"));
                int cristales = Integer.parseInt(protoss.getAttribute("cristales"));
                int capacidadCuracion = Integer.parseInt(protoss.getAttribute("energia"));
                String rol = protoss.getAttribute("rol");

                Protoss temp = new Protoss(rol,controlState, lifeState,cristales, gas, energia, capacidadCuracion);
                loadedWorld.addObject(temp, x, y);   
            }   
            } 
            else{
               NodeList terranNode = extraterrestres.getElementsByTagName("Terran").item(0).getChildNodes();
            for(int i=0; i< terranNode.getLength();i++)
            {
             Element terran = (Element) terranNode.item(i);

                int x = Integer.parseInt(terran.getAttribute("X"));
                int y = Integer.parseInt(terran.getAttribute("Y"));

                boolean lifeState = Boolean.parseBoolean(terran.getAttribute("estadoDeVida"));
                boolean controlState = Boolean.parseBoolean(terran.getAttribute("estadoDeMovimiento"));
                int energia = Integer.parseInt(terran.getAttribute("energia"));
                int gas = Integer.parseInt(terran.getAttribute("gas"));
                int cristales = Integer.parseInt(terran.getAttribute("cristales"));
                int capacidadCuracion = Integer.parseInt(terran.getAttribute("energia"));
                String rol = terran.getAttribute("rol");

                Zerg temp = new Zerg(rol,controlState, lifeState,cristales, gas, energia, capacidadCuracion);
                loadedWorld.addObject(temp, x, y);   
            }  
            }
        }
        Element GasDeposit = (Element) doc.getElementsByTagName("GasDeposits").item(0);
        NodeList depositosGas = GasDeposit.getChildNodes();
        for (int i = 0; i < depositosGas.getLength(); i++) {

                Element depositoGas = (Element) depositosGas.item(i);

                int x = Integer.parseInt(depositoGas.getAttribute("X"));
                int y = Integer.parseInt(depositoGas.getAttribute("Y"));

                GasDeposit temp = new GasDeposit();
                
                loadedWorld.addObject(temp, x, y);
            }
            Element Deposit = (Element) doc.getElementsByTagName("Deposito").item(0);
            NodeList deposito = Deposit.getChildNodes();
        for (int i = 0; i < deposito.getLength(); i++) {

                Element depositos = (Element) deposito.item(i);

                int x = Integer.parseInt(depositos.getAttribute("X"));
                int y = Integer.parseInt(depositos.getAttribute("Y"));
                
                int cantMax = Integer.parseInt(depositos.getAttribute("cantMaxima"));
                Deposito temp = new Deposito(cantMax);
                
                loadedWorld.addObject(temp, x, y);
            }
            Element Comando = (Element) doc.getElementsByTagName("Comando").item(0);
            NodeList comando = Comando.getChildNodes();
            for (int i = 0; i < comando.getLength(); i++) {

                Element comandos = (Element) comando.item(i);

                int x = Integer.parseInt(comandos.getAttribute("X"));
                int y = Integer.parseInt(comandos.getAttribute("Y"));
                
                String nombre = comandos.getAttribute("Nombre");
                Comando temp = new Comando(nombre);
                
                loadedWorld.addObject(temp, x, y);
            }
            Element HealthCenter = (Element) doc.getElementsByTagName("HealthCenter").item(0);
            NodeList healthCenter = HealthCenter.getChildNodes();
            for (int i = 0; i < healthCenter.getLength(); i++) {

                Element healthcenter = (Element) healthCenter.item(i);

                int x = Integer.parseInt(healthcenter.getAttribute("X"));
                int y = Integer.parseInt(healthcenter.getAttribute("Y"));
                
                int numCelda = Integer.parseInt(healthcenter.getAttribute("Celdas"));
                boolean estado = Boolean.parseBoolean(healthcenter.getAttribute("Estado"));
                HealthCenter temp = new HealthCenter(numCelda,estado);
                
                loadedWorld.addObject(temp, x, y);
            }
            Element Obstaculo = (Element) doc.getElementsByTagName("Obstaculo").item(0);
            NodeList obstaculo = Obstaculo.getChildNodes();
            for (int i = 0; i < obstaculo.getLength(); i++) {

                Element obstaculos = (Element) obstaculo.item(i);

                int x = Integer.parseInt(obstaculos.getAttribute("X"));
                int y = Integer.parseInt(obstaculos.getAttribute("Y"));
                
                Obstaculo temp = new Obstaculo();
                
                loadedWorld.addObject(temp, x, y);
            }
            int width = loadedWorld.getWidth();
            int height = loadedWorld.getHeight();
            SmallScreen loading = new SmallScreen("Carga exitosa!",height/2,width/2,20);
            loadedWorld.addObject(loading,width/2,height/2);
}
 catch (Exception e) {
            //e.printStackTrace();
            //System.out.println("ERROR");
            throw e;
        }

        return loadedWorld;
}
public String getDefaultSavePath()
{
 return defaultSavePath;   
}
}

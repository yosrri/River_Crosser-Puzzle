package MyWork; 
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;

public class SaveGame {
    List<ICrosser> leftBank;
    List<ICrosser>rightBank;
    String Path;
    int noSails;
    boolean raft;




    public SaveGame(List<ICrosser> leftBank, List<ICrosser> rightBank, String Path,int noSails,boolean raft){
        this.leftBank=leftBank;
        this.rightBank=rightBank;
        this.Path=Path;
        this.noSails=noSails;
        this.raft = raft;
    }

    public void saveProgress(){


        try {


            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();

            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

            Document document = documentBuilder.newDocument();


            // root element
            Element root;
            if(Path.equalsIgnoreCase("Stroyone"))
             root = document.createElement("storyone");
            else
            	root = document.createElement("storytwo");
            document.appendChild(root);
            Element leftbank = document.createElement("leftbank");
            root.appendChild(leftbank);

            for (int temp = 0; temp < leftBank.size(); temp++) {


                Element crosser = document.createElement("crosserleft");
                crosser.appendChild(document.createTextNode(leftBank.get(temp).getLabelTOBeShown()));
                leftbank.appendChild(crosser);

            }
            Element rightbank = document.createElement("rightbank");
            root.appendChild(rightbank);
            for (int temp = 0; temp < rightBank.size(); temp++) {
                Element crosser = document.createElement("crosserright");
                crosser.appendChild(document.createTextNode(rightBank.get(temp).getLabelTOBeShown()));
                rightbank.appendChild(crosser);
            }

            Element boat = document.createElement("boat");
            root.appendChild(boat);

            Element noOfSails = document.createElement("NumberOfSails");
            noOfSails.appendChild(document.createTextNode(String.valueOf(noSails)));
            boat.appendChild(noOfSails);

            Element boatPlace = document.createElement("BoatIsOn");
            boatPlace.appendChild(document.createTextNode(String.valueOf(raft)));
            boat.appendChild(boatPlace);





            // create the xml file
            //transform the DOM Object to an XML File
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(Path+".xml"));

            // If you use
            // StreamResult result = new StreamResult(System.out);
            // the output will be pushed to the standard output ...
            // You can use that for debugging

            transformer.transform(domSource, streamResult);

            System.out.println("Done creating XML File");

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();

        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }
}
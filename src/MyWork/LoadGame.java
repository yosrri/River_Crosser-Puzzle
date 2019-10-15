package MyWork;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.List;


public class LoadGame {

    Farmer farmer = new Farmer();
    Farmer farmer2 = new Farmer();
    Farmer farmer3 = new Farmer();
    Farmer farmer4 = new Farmer();
    Herbivorous sheep = new Herbivorous();
    Carnivorous wolf = new Carnivorous();
    Plant plant = new Plant();
    Boat boat = Boat.getInstance();
    
    
    List<ICrosser> leftBank;
    List<ICrosser>rightBank;
    String Path;
    int noSails;
    boolean raft;




    public LoadGame(List<ICrosser> leftBank, List<ICrosser> rightBank, String Path,int noSails,boolean raft){
        this.leftBank=leftBank;
        this.rightBank=rightBank;
        this.Path=Path;
        this.noSails=noSails;
        this.raft = raft;
    }

    

    public void loadProgress() {
    	if(Path.equalsIgnoreCase("storyone")) {
    	try {
        	
            File inputFile = new File("storyone.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList lbList = doc.getElementsByTagName("leftbank");
            NodeList clList = doc.getElementsByTagName("crosserleft");
            NodeList rbList = doc.getElementsByTagName("rightbank");
            NodeList crList = doc.getElementsByTagName("crosserright");
            NodeList bList = doc.getElementsByTagName("boat");
            NodeList nSList = doc.getElementsByTagName("NumberOfsails");
            NodeList bPList = doc.getElementsByTagName("BoatIsOn");
            System.out.println("----------------------------");


            Node nNode = lbList.item(0);
            for (int temp = 0; temp < clList.getLength(); temp++) {

                // System.out.println("\nCurrent Element :" + nNode.getNodeName());

                Element eElement = (Element) nNode;
//             System.out.println("LEFT : "
//                     + eElement
//                     .getElementsByTagName("crosserleft")
//                     .item(temp)
//                     .getTextContent());


                if (eElement.getElementsByTagName("crosserleft").item(temp).getTextContent().equals("farmer")) {
                    farmer.setLabelToBeShown(eElement.getElementsByTagName("crosserleft").item(temp).getTextContent());
                    farmer.setEatingRank(Integer.MAX_VALUE);

                    leftBank.add(farmer);
                } else if (eElement.getElementsByTagName("crosserleft").item(temp).getTextContent().equals("plant")) {
                    plant.setLabelToBeShown(eElement.getElementsByTagName("crosserleft").item(temp).getTextContent());
                    plant.setEatingRank(Integer.MIN_VALUE);
                    leftBank.add(plant);
                } else if (eElement.getElementsByTagName("crosserleft").item(temp).getTextContent().equals("sheep")) {
                    sheep.setLabelToBeShown(eElement.getElementsByTagName("crosserleft").item(temp).getTextContent());
                    sheep.setEatingRank(1);
                    leftBank.add(sheep);

                } else if (eElement.getElementsByTagName("crosserleft").item(temp).getTextContent().equals("wolf")) {
                    wolf.setLabelToBeShown(eElement.getElementsByTagName("crosserleft").item(temp).getTextContent());
                    wolf.setEatingRank(2);
                    leftBank.add(wolf);
                }
            }


            Node mNode = rbList.item(0);
            for (int temp = 0; temp < crList.getLength(); temp++) {


                Element eElement = (Element) mNode;


                if (eElement.getElementsByTagName("crosserright").item(temp).getTextContent().contains("farmer")) {
                    farmer.setLabelToBeShown(eElement.getElementsByTagName("crosserright").item(temp).getTextContent());
                    rightBank.add(farmer);
                } else if (eElement.getElementsByTagName("crosserright").item(temp).getTextContent().contains("plant")) {
                    plant.setLabelToBeShown(eElement.getElementsByTagName("crosserright").item(temp).getTextContent());
                    rightBank.add(plant);
                } else if (eElement.getElementsByTagName("crosserright").item(temp).getTextContent().contains("sheep")) {
                    sheep.setLabelToBeShown(eElement.getElementsByTagName("crosserright").item(temp).getTextContent());
                    rightBank.add(sheep);


                } else if (eElement.getElementsByTagName("crosserright").item(temp).getTextContent().contains("wolf")) {
                    wolf.setLabelToBeShown(eElement.getElementsByTagName("crosserright").item(temp).getTextContent());
                    rightBank.add(wolf);
                }
            }


            Node bNode = bList.item(0);

            for (int temp = 0; temp < bPList.getLength(); temp++) {
                Element eElement = (Element) bNode;
                eElement.getElementsByTagName("NumberOfSails").item(0).getTextContent();
                System.out.println(eElement.getElementsByTagName("NumberOfSails").item(0).getTextContent());
                boat.setNoSails(Integer.valueOf(eElement.getElementsByTagName("NumberOfSails").item(0).getTextContent()));
                eElement.getElementsByTagName("BoatIsOn").item(0).getTextContent();
                System.out.println(eElement.getElementsByTagName("BoatIsOn").item(0).getTextContent());
            }
        

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    	if(Path.equalsIgnoreCase("storytwo")) {
        	try {
            	
                File inputFile = new File("storyTwo.xml");
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(inputFile);
                doc.getDocumentElement().normalize();
                System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
                NodeList lbList = doc.getElementsByTagName("leftbank");
                NodeList clList = doc.getElementsByTagName("crosserleft");
                NodeList rbList = doc.getElementsByTagName("rightbank");
                NodeList crList = doc.getElementsByTagName("crosserright");
                NodeList bList = doc.getElementsByTagName("boat");
                NodeList nSList = doc.getElementsByTagName("NumberOfsails");
                NodeList bPList = doc.getElementsByTagName("BoatIsOn");
                System.out.println("----------------------------");


                Node nNode = lbList.item(0);
                for (int temp = 0; temp < clList.getLength(); temp++) {

                    // System.out.println("\nCurrent Element :" + nNode.getNodeName());

                    Element eElement = (Element) nNode;
//                 System.out.println("LEFT : "
//                         + eElement
//                         .getElementsByTagName("crosserleft")
//                         .item(temp)
//                         .getTextContent());


                    if (eElement.getElementsByTagName("crosserleft").item(temp).getTextContent().equals("90")) {
                        farmer.setLabelToBeShown(eElement.getElementsByTagName("crosserleft").item(temp).getTextContent());
                        farmer.setEatingRank(Integer.MAX_VALUE);
                        farmer.setWeight(90.0);
                        leftBank.add(farmer);
                    } else if (eElement.getElementsByTagName("crosserleft").item(temp).getTextContent().equals("80")) {
                        farmer2.setLabelToBeShown(eElement.getElementsByTagName("crosserleft").item(temp).getTextContent());
                        farmer2.setEatingRank(Integer.MAX_VALUE);
                        farmer2.setWeight(80.0);
                        leftBank.add(farmer2);
                    } else if (eElement.getElementsByTagName("crosserleft").item(temp).getTextContent().equals("60")) {
                        farmer3.setLabelToBeShown(eElement.getElementsByTagName("crosserleft").item(temp).getTextContent());
                        farmer3.setEatingRank(Integer.MAX_VALUE);
                        farmer3.setWeight(60.0);
                        leftBank.add(farmer3);

                    } else if (eElement.getElementsByTagName("crosserleft").item(temp).getTextContent().equals("40")) {
                        farmer4.setLabelToBeShown(eElement.getElementsByTagName("crosserleft").item(temp).getTextContent());
                        farmer4.setEatingRank(Integer.MAX_VALUE);
                        farmer4.setWeight(40.0);
                        leftBank.add(farmer4);
                    } else if (eElement.getElementsByTagName("crosserleft").item(temp).getTextContent().equals("20")) {
                        sheep.setLabelToBeShown(eElement.getElementsByTagName("crosserleft").item(temp).getTextContent());
                        sheep.setEatingRank(1);
                        sheep.setWeight(20.0);
                        leftBank.add(sheep);
                    }
                }


                Node mNode = rbList.item(0);
                for (int temp = 0; temp < crList.getLength(); temp++) {


                    Element eElement = (Element) mNode;


                    if (eElement.getElementsByTagName("crosserright").item(temp).getTextContent().contains("90")) {
                        farmer.setLabelToBeShown(eElement.getElementsByTagName("crosserright").item(temp).getTextContent());
                        rightBank.add(farmer);
                    } else if (eElement.getElementsByTagName("crosserright").item(temp).getTextContent().contains("80")) {
                        farmer2.setLabelToBeShown(eElement.getElementsByTagName("crosserright").item(temp).getTextContent());
                        rightBank.add(farmer2);
                    } else if (eElement.getElementsByTagName("crosserright").item(temp).getTextContent().contains("60")) {
                    	farmer3.setLabelToBeShown(eElement.getElementsByTagName("crosserright").item(temp).getTextContent());
                        rightBank.add(farmer3);


                    } else if (eElement.getElementsByTagName("crosserright").item(temp).getTextContent().contains("40")) {
                    	farmer4.setLabelToBeShown(eElement.getElementsByTagName("crosserright").item(temp).getTextContent());
                        rightBank.add(farmer4);
                    }
                    else if (eElement.getElementsByTagName("crosserright").item(temp).getTextContent().contains("20")) {
                        sheep.setLabelToBeShown(eElement.getElementsByTagName("crosserright").item(temp).getTextContent());
                        rightBank.add(sheep);
                    }
                }


                Node bNode = bList.item(0);

                for (int temp = 0; temp < bPList.getLength(); temp++) {
                    Element eElement = (Element) bNode;
                    eElement.getElementsByTagName("NumberOfSails").item(0).getTextContent();
                    System.out.println(eElement.getElementsByTagName("NumberOfSails").item(0).getTextContent());
                    boat.setNoSails(Integer.valueOf(eElement.getElementsByTagName("NumberOfSails").item(0).getTextContent()));
                    eElement.getElementsByTagName("BoatIsOn").item(0).getTextContent();
                    System.out.println(eElement.getElementsByTagName("BoatIsOn").item(0).getTextContent());
                }
            

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}





package jaxb_lab2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author DELL
 */
public class JAXB_lab2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        try {
            //step 1 : Create the client (Context)
            JAXBContext context = JAXBContext.newInstance("jaxb_lab2");
            
            //step2 : Unmarshal: xml -> objects
            Unmarshaller unmarsh = context.createUnmarshaller();
            JAXBElement JAXBMyMsg = (JAXBElement) unmarsh.unmarshal(new File("message.xml"));
            MyMsgType myMsgType = (MyMsgType) JAXBMyMsg.getValue();
            
            //step3 : read print
            
 
            System.out.println("Person name=" +  myMsgType.getMsg());
            System.out.println("Person name=" +  myMsgType.getHeader());
            
            MsgType msgType = (MsgType)myMsgType.getMsg().get(0);
            
            myMsgType.setHeader("RADWAAA");
            ObjectFactory factory = new ObjectFactory();
            MsgType type = factory.createMsgType();
            type.setBody("hello");
            type.setFrom("ana");
            type.setTo("radwa");
            
            myMsgType.msg.add(type);
            
            JAXBElement person = factory.createMyMsg(myMsgType);
            Marshaller marsh = context.createMarshaller();
            marsh.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marsh.marshal(person, new FileOutputStream("output.xml"));

            
            
            
            
//            System.out.println("Person name=" + personType.getName());
//            AddressType address = (AddressType) personType.getAddress().get(0);
//            AddressType address2 = (AddressType) personType.getAddress().get(1);
//
//            System.out.println("First Address:" + "Street=" + address.getStreet() + "Number=" + address.getNumber());
//            System.out.println("second Address:" + "Street=" + address2.getStreet() + "Number=" + address2.getNumber());


            
            
        } catch (JAXBException ex) {
            Logger.getLogger(JAXB_lab2.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
    }
    
}

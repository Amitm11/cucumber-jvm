package helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFPicture;
import org.apache.poi.xwpf.usermodel.XWPFPictureData;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlToken;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTInline;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;


public class CustomXWPFDocument extends XWPFDocument
{
    public CustomXWPFDocument(InputStream in) throws IOException
    {
        super(in);
    }

    public void createPicture(String blipId,int id, int width, int height)
    {
        final int EMU = 9525;
        width *= EMU;
        height *= EMU;
        //String blipId = getAllPictures().get(id).getPackageRelationship().getId();


        CTInline inline = createParagraph().createRun().getCTR().addNewDrawing().addNewInline();
        String picXml = "" +
                "<a:graphic xmlns:a=\"http://schemas.openxmlformats.org/drawingml/2006/main\">" +
                "   <a:graphicData uri=\"http://schemas.openxmlformats.org/drawingml/2006/picture\">" +
                "      <pic:pic xmlns:pic=\"http://schemas.openxmlformats.org/drawingml/2006/picture\">" +
                "         <pic:nvPicPr>" +
                "            <pic:cNvPr id=\"" + id + "\" name=\"Generated\"/>" +
                "            <pic:cNvPicPr/>" +
                "         </pic:nvPicPr>" +
                "         <pic:blipFill>" +
                "            <a:blip r:embed=\"" + blipId + "\" xmlns:r=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships\"/>" +
                "            <a:stretch>" +
                "               <a:fillRect/>" +
                "            </a:stretch>" +
                "         </pic:blipFill>" +
                "         <pic:spPr>" +
                "            <a:xfrm>" +
                "               <a:off x=\"0\" y=\"0\"/>" +
                "               <a:ext cx=\"" + width + "\" cy=\"" + height + "\"/>" +
                "            </a:xfrm>" +
                "            <a:prstGeom prst=\"rect\">" +
                "               <a:avLst/>" +
                "            </a:prstGeom>" +
                "         </pic:spPr>" +
                "      </pic:pic>" +
                "   </a:graphicData>" +
                "</a:graphic>";

        //CTGraphicalObjectData graphicData = inline.addNewGraphic().addNewGraphicData();
        XmlToken xmlToken = null;
        try
        {
            xmlToken = XmlToken.Factory.parse(picXml);
        }
        catch(XmlException xe)
        {
            xe.printStackTrace();
        }
        inline.set(xmlToken);
        //graphicData.set(xmlToken);

        inline.setDistT(0);
        inline.setDistB(0);
        inline.setDistL(0);
        inline.setDistR(0);

        CTPositiveSize2D extent = inline.addNewExtent();
        extent.setCx(width);
        extent.setCy(height);

        CTNonVisualDrawingProps docPr = inline.addNewDocPr();
        docPr.setId(id);
        docPr.setName("Picture " + id);
        docPr.setDescr("Generated");
    }
    
    public static void convertDocToPdf() throws Throwable
    {
    	System.out.println("Conversion started");
    	String inputFile = "D:/SeleniumInstallables/SmokeTestEvidence.docx";
    	String outputFile = "D:/SeleniumInstallables/SmokeTestEvidence.pdf";
    	System.out.println("inputFile:" + inputFile + ", outputFile:"+ outputFile);
    	
    	FileInputStream in = new FileInputStream(inputFile);
    	XWPFDocument doc = new XWPFDocument(in);
    	File file = new File(outputFile);
    	FileOutputStream fileOut = new FileOutputStream(file);
    	Document document = new Document(PageSize.A4, 72, 72, 72, 72);
    	PdfWriter pWriter = PdfWriter.getInstance(document, fileOut);
    	pWriter.setInitialLeading(10);
    	List<XWPFParagraph> plist = doc.getParagraphs();
    	
    	document.addAuthor("Amit Maheshwari");
    	document.addTitle("Word to PDF conversion");
    	document.open();

    	for(int i = 0; i <plist.size(); i++)
    	{
    		XWPFParagraph para = plist.get(i);
    		List<XWPFRun> runs = para.getRuns();
    		for(int j = 0; j < runs.size(); j++)
    		{
    			XWPFRun run = runs.get(j);
    			List<XWPFPicture> picList = run.getEmbeddedPictures();
    			Iterator<XWPFPicture> iterator = picList.iterator();
    			while(iterator.hasNext())
    			{
    				XWPFPicture pic = iterator.next();
    				XWPFPictureData picData = pic.getPictureData();
    				byte[] bytePic = picData.getData();
    				Image img = Image.getInstance(bytePic);
    				document.add(img);
    			}
    		   		
    			String text = run.getText(-1);
    			byte[] bs;
    			if (text != null)
    			{
    				bs = text.getBytes();
    				String str = new String(bs, "UTF-8");
    				Chunk chunk = new Chunk(str);
    				document.add(chunk);
    			}
    		}
    		document.add(new Chunk(Chunk.NEWLINE));
    	}
    	document.close();
    	System.out.println("Conversion complete");
    }
}


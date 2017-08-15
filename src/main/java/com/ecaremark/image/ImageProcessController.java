package com.ecaremark.image;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ecaremark.entity.MemberInfo;
import com.ecaremark.entity.ProcessImageRequest;

@RestController
public class ImageProcessController {
	
	private IImageProcessor imageProcessor;
	
	@Autowired
	public void setImageProcessor(IImageProcessor imageProcessor) {
		this.imageProcessor = imageProcessor;
	}
	
	@GetMapping(value = "/test") 
	public String test() throws IOException {
		return "Success";
	}
	
	@GetMapping(value = "/getTestImage") 
	public ResponseEntity<Resource> process() throws IOException {
		MemberInfo mi = StandAloneImageProcessorApp.createSampleMember();
		File file = ResourceUtils.getFile(this.getClass().getResource("/rsz_img_7358.jpg"));
		InputStream ip = new FileInputStream(file);
		BufferedImage finalImg = imageProcessor.createImageWithHeader(ip, mi);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(finalImg, "jpg", baos);
		byte[] bytes = baos.toByteArray();
		System.out.println("Encoded 64 base file length: " + bytes.length);
		ByteArrayResource bar = new ByteArrayResource(bytes);
		return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"new_" + file.getName() + "\"")
                .body(bar);
		
	}
	
	@GetMapping(value = "/getBase64Image") 
	public ProcessImageRequest getBase64Image() throws IOException {
		return createRequest();
	}
	
	/** The real deal. Passing a json request that contains a jpg image in base64 encoded format along 
	 * with the member info, it will append a header image on top of the original image and send back
	 * to the caller. 
	 * Note: the response is in binary format. 
	 * 
	 * Request sample:
			{  
			   "memberInfo":{  
			      "memberId":891234567,
			      "fname":"Jane",
			      "lname":"Cool",
			      "dob":"11/01/1994",
			      "address":{  
			         "streetAddress":"901 E Colins St.",
			         "city":"Richardson",
			         "zipCode":"75081",
			         "state":"Texas"
			      },
			      "phone":"214-555-6789",
			      "email":"Jane.Doe@fictious.com"
			   },
			   "fileName":"tartar_01",
			   "imageContentBase64":"/9j/4AAQSkZJRgABAQEASABIAAD....."
			}
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@PostMapping("/transform") 
    public ResponseEntity<Resource>  addHeaderToImage(@RequestBody ProcessImageRequest request) throws IOException {
		System.out.println("Received request, base64 encoded length: " + request.imageContentBase64.length());
		//return createRequest();
		byte[]  bytes = Base64.getDecoder().decode(request.imageContentBase64 );
		InputStream is = new ByteArrayInputStream(bytes);
		BufferedImage finalImage = imageProcessor.createImageWithHeader(is, request.memberInfo);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(finalImage, "jpg", baos);
		ByteArrayResource bar = new ByteArrayResource(baos.toByteArray());
		
		return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"new_" + request.fileName + "\"")
                .header("Content-Type", "image/jpg")
                .body(bar);
	}

	private ProcessImageRequest createRequest() throws FileNotFoundException {
		ProcessImageRequest req = new ProcessImageRequest();
		req.memberInfo = StandAloneImageProcessorApp.createSampleMember();
		File file = ResourceUtils.getFile(this.getClass().getResource("/rsz_img_7358.jpg"));
		byte[] bytes = readContentIntoByteArray(file);
		req.imageContentBase64 =  Base64.getEncoder().encodeToString(bytes);
		return req;
	}
	
	@PostMapping("/load")
    public ResponseEntity<Resource>  handleFileUpload(@RequestParam("file") MultipartFile file, @RequestBody ProcessImageRequest request) throws IOException {
		InputStream is = file.getInputStream();
		MemberInfo mi = request.memberInfo;
		BufferedImage finalImage = imageProcessor.createImageWithHeader(is, mi);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(finalImage, "jpg", baos);
		ByteArrayResource bar = new ByteArrayResource(baos.toByteArray());
		return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"new_" + file.getOriginalFilename() + "\"")
                .body(bar);

        
    }
	
	 private static byte[] readContentIntoByteArray(File file)
	   {
	      FileInputStream fileInputStream = null;
	      byte[] bFile = new byte[(int) file.length()];
	      try
	      {
	         //convert file into array of bytes
	         fileInputStream = new FileInputStream(file);
	         fileInputStream.read(bFile);
	         fileInputStream.close();
	         for (int i = 0; i < bFile.length; i++)
	         {
	            System.out.print((char) bFile[i]);
	         }
	      }
	      catch (Exception e)
	      {
	         e.printStackTrace();
	      }
	      return bFile;
	   }
}

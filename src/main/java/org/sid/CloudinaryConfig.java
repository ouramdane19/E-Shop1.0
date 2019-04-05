package org.sid;

import com.cloudinary.Cloudinary;
import com.cloudinary.Singleton;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;

import org.sid.entities.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.Map;

@Component
public class CloudinaryConfig {
    private Cloudinary cloudinary;
    @Autowired
    public CloudinaryConfig(@Value("${cloudinary.apikey}") String key,
                            @Value("${cloudinary.apisecret}") String secret,
                            @Value("${cloudinary.cloudname}") String cloud){
        cloudinary = Singleton.getCloudinary();
        cloudinary.config.cloudName=cloud;
        cloudinary.config.apiSecret=secret;
        cloudinary.config.apiKey=key;
    }

    public String upload(Object file){
        try{
            return (String) cloudinary.uploader().upload(file, ObjectUtils.emptyMap()).get("secure_url");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
//    public String upload(String file) {
// 
//		try {
//			String picture_url = (String) cloudinary.uploader().upload(file, ObjectUtils.emptyMap()).get("secure_url");
//			return picture_url;
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//	
//    }
//    public String createUrl(String name, int width, int height, String action){
//        return cloudinary.url()
//                .transformation(new Transformation().width(width).height(height)
//		.border("2px_solid_black").crop(action))
//                .imageTag(name);
//    }
}


package org.sid.seeders;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.sid.dao.CategorieRepository;
import org.sid.dao.ProduitRepository;
import org.sid.entities.Categorie;
import org.sid.entities.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.github.javafaker.Faker;




@Component
public class DataBaseSeeder {
	private CategorieRepository categorieRepository;
	private ProduitRepository produitRepository;
	Cloudinary cloudinary = new Cloudinary("cloudinary://127997472188269:XKQYBduBIma62gy8nKtykDZWZAE@deesjjv4h");
	Faker faker = new Faker(new Locale("fr"));

    @Autowired
    public void  DatabaseSeeder(CategorieRepository categorieRepository, ProduitRepository produitRepository) 
    	{
        this.categorieRepository = categorieRepository;
        this.produitRepository = produitRepository;
        }
    
	@EventListener
	public void seed(ContextRefreshedEvent event) {
	    //seedCategoryTable();
		//idsCategories();
		//seedProductTable();
		
	}
	
	

    private  List<Long> idsCategories() {
    	// TODO Auto-generated method stub
    	//get a collection of all the ids.
    	List<Long> ids = categorieRepository.findAll().stream()
    			.map(Categorie::getIdCategorie).collect(Collectors.toList());
    	System.out.println(ids);
    	return ids;
	}
    


	// ******************SEED CATEGORIES**************//
	private void seedCategoryTable() {
		// Destroy all categories
		categorieRepository.deleteAll();
		// add categories
	    for (int i = 0; i < 10; i++) {
	    	String categorieName = faker.book().genre();
	    	String description = faker.lorem().paragraph();
	    	Collection<Produit> produits = produitRepository.findAll();
	   		Categorie cat1 = new Categorie(categorieName,description,produits);	
	   		categorieRepository.save(cat1);	
	    }
	    System.out.println("**********Product table seeded********");
	    System.out.println("le nombre de produits est: " + produitRepository.count());
	}
	

	//************* SEED PRODUCTS ****************//
	
	private void seedProductTable() {
		// Destroy all products
		produitRepository.deleteAll();
		// Add Products 
	    for (int i = 0; i < 10; i++) {
	        // generate fake products
	        for (int j = 0; j < idsCategories().size(); j++) {
	        	String ProductName = faker.book().title();
		    	double ProductPrice = faker.number().numberBetween(1, 22);
		    	Integer ProductQuantity = faker.number().numberBetween(1, 100);
		        String picture = faker.avatar().image();
		        String ProductDescription = faker.lorem().paragraph();
	        	Categorie cat = categorieRepository.getOne(idsCategories().get(j));
	        	
			    // upoload pictures to cloudinary	
			    	try {
			    		cloudinary.uploader().upload(picture, ObjectUtils.emptyMap());
						System.out.println(cloudinary.uploader().upload(picture, ObjectUtils.emptyMap()).get("secure_url"));
						String picture_url = (String) cloudinary.uploader().upload(picture, ObjectUtils.emptyMap()).get("secure_url");
						produitRepository.save(new Produit(ProductName,ProductDescription, ProductPrice,ProductQuantity,picture_url,cat));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
	          
		}
	    System.out.println("**********Product table seeded********");
		System.out.println("le nombre de produits est: " + produitRepository.count());
	}
	
}






































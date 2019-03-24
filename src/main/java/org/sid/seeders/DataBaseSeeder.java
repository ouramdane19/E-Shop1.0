package org.sid.seeders;

import java.util.Locale;

import org.sid.dao.CategorieRepository;
import org.sid.dao.ProduitRepository;
import org.sid.entities.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;


@Component
public class DataBaseSeeder {
	
	private CategorieRepository categorieRepository;
	private ProduitRepository produitRepository;
	Faker faker = new Faker(new Locale("fr"));
	
	String streetAddress = faker.address().streetAddress(); // 60018 Sawayn Brooks Suite 449
    @Autowired
    public void  DatabaseSeeder(CategorieRepository categorieRepository, ProduitRepository produitRepository) 
    	{
        this.categorieRepository = categorieRepository;
        this.produitRepository = produitRepository;
        }
    



@EventListener
public void seed(ContextRefreshedEvent event) {
    //seedCategoryTable();
    seedProductTable();
}


/*private void seedCategoryTable() {
	// TODO Auto-generated method stub
	// TODO Auto-generated method stub
	String dc0 = "Technology", dc1 = "General", dc2 = "Life";
        String sql = "SELECT category FROM categories c WHERE c.category IN (\"" + dc0 + "\", \"" + dc1 + "\", \"" + dc2 + "\")";
        List<Category> rs = jdbcTemplate.query(sql, (resultSet, rowNum) -> null);
        if(rs == null || rs.size() <= 0) {
            Category c = new Category("Technology");
            Category c2 = new Category("General");
            Category c3 = new Category("Life");
            categoryRepository.save(Arrays.asList(c, c2, c3));
            logger.info("category table seeded");
        }else {
            logger.trace("Category Seeding Not Required");
        }
	
}*/


private void seedProductTable() {
	// ajout de produits 
    for (int i = 0; i < 50; i++) {
    	String ProductName = faker.book().title();
    	double ProductPrice = faker.number().numberBetween(1, 22);
    	Integer ProductQuantity = faker.number().numberBetween(1, 100);
		produitRepository.save(new Produit(ProductName,ProductPrice,ProductQuantity));	
	}
    System.out.println("**********Product table seeded********");
}

}






































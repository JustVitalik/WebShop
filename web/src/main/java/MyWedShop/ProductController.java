package MyWedShop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
public class ProductController {

    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/")
    public String productFindAll() {
        return "index";
    }

    @PostMapping("/product/saveProduct")
    public String saveProduct(@RequestParam("price") int price, @RequestParam("name") String name,
                              @RequestParam("description") String description,
                              @RequestParam("image") MultipartFile file) throws IOException {

        Date createDate = new Date();
        Product product = new Product(price, name, description, file.getBytes(), createDate);
        productRepository.save(product);
        return "redirect:/";
    }

    @GetMapping("/product/show")
    public String productDhow(Model model) {
        List<Product> productList = productRepository.findAll();
        model.addAttribute("products", productList);
        return "showAll";
    }

    @GetMapping("/product/display/{id}")
    void showImage(@PathVariable("id") Long id, HttpServletResponse response, Model model) throws IOException {
        Product product = productRepository.getById(id);
        response.setContentType(("image/jpeg, image/jpg, image/png, image/gif"));
        response.getOutputStream().write(product.getImages());
        response.getOutputStream().close();
        model.addAttribute("productIm", product);

    }

    @GetMapping("/product/details/{id}")
    public String productDetails(@PathVariable("id") Long id, Model model) {
        if (id != 0) {
            Product product = productRepository.getById(id);
            model.addAttribute("id", product.getId());
            model.addAttribute("description", product.getDescription());
            model.addAttribute("name", product.getName());
            model.addAttribute("price", product.getPrice());
            return "productDetails";
        }
        return "index";

    }

    @PostMapping("/product/delete/{id}")
    public String productDelete(@PathVariable("id") Long id) {
        productRepository.deleteById(id);
        return "redirect:/product/show";
    }

    @PostMapping("/product/edit/{id}")
    public String editDelete(@PathVariable("id") Long id, Model model) {
        if (productRepository.findById(id).isPresent())
            model.addAttribute("product", productRepository.findById(id).get());
        return "edit";
    }

    @PostMapping("/{id}")
    public String editProduct(@RequestParam("price") int price, @RequestParam("name") String name,
                              @RequestParam("description") String description,
                              @RequestParam("image") MultipartFile file) throws IOException {

        Date createDate = new Date();
        Product product = new Product(price, name, description, file.getBytes(), createDate);
        productRepository.save(product);
        return "redirect:/product/show";

    }

//    @GetMapping("/product/find/{name}")
//    public String findproduct(@RequestParam("name") String name, Model model) {
//
//        model.addAttribute("productFind", productRepository.findByName(name));
//
//        return "productDetails";
//    }
}

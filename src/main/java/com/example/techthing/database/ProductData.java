package com.example.techthing.database;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.techthing.entity.Category;
import com.example.techthing.entity.Product;
import com.example.techthing.repository.CategoryRepository;
import com.example.techthing.repository.ProductRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductData {
        ProductRepository productRepository;
        CategoryRepository categoryRepository;

        public void createBase() {
                if (this.productRepository.count() == 0) {
                        // category
                        Category phone = this.categoryRepository.findByName("Phone");
                        Category tablet = this.categoryRepository.findByName("Tablet");
                        Category laptop = this.categoryRepository.findByName("Laptop");
                        Category watch = this.categoryRepository.findByName("Watch");
                        Category sound = this.categoryRepository.findByName("Sound");
                        Category accessory = this.categoryRepository.findByName("Accessory");
                        Category pc = this.categoryRepository.findByName("PC");
                        Category tv = this.categoryRepository.findByName("TV");
                        // iphone
                        Product iphone = Product.builder()
                                        .name("iPhone 15 Pro Max 256GB")
                                        .description("iPhone 15 Pro Max thiết kế mới với chất liệu titan chuẩn hàng không vũ trụ bền bỉ. Khả năng chụp ảnh đỉnh cao của iPhone 15 bản Pro Max đến từ camera chính 48MP")
                                        .image("https://res.cloudinary.com/dygjmljlg/image/upload/v1716130858/df7fmtiqcpdfaeeplwxg.jpg")
                                        .price(29290000)
                                        .category(phone)
                                        .build();
                        // samsung
                        Product samsung = Product.builder()
                                        .name("Samsung Galaxy S23 Ultra 256GB")
                                        .description("Điện thoại Samsung Galaxy S23 Ultra có kiểu dáng thanh lịch kết hợp với những đột phá công nghệ đã tạo nên siêu phẩm Flagship nhà Samsung.")
                                        .image("https://res.cloudinary.com/dygjmljlg/image/upload/v1716130924/px0v5ptcot2oqcie7xwv.jpg")
                                        .price(21990000)
                                        .category(phone)
                                        .build();
                        // ipad
                        Product ipad = Product.builder()
                                        .name("iPad Pro M4 11 inch Wifi 256GB")
                                        .description("iPad Pro M4 11 inch là mẫu máy tính bảng dành cho các công việc chuyên nghiệp khi được trang bị con chip M4 với hiệu năng vượt bậc.")
                                        .image("https://res.cloudinary.com/dygjmljlg/image/upload/v1716130715/zdnlgzh4penvsglkpgdm.jpg")
                                        .price(28990000)
                                        .category(tablet)
                                        .build();
                        // mac book
                        Product macBook = Product.builder()
                                        .name("Apple MacBook Air M1 256GB 2020")
                                        .description("Macbook Air M1 là dòng sản phẩm có thiết kế mỏng nhẹ, sang trọng và tinh tế cùng với đó là giá thành phải chăng")
                                        .image("https://res.cloudinary.com/dygjmljlg/image/upload/v1716131027/puhcxexll7fyducgs6nj.jpg")
                                        .price(18190000)
                                        .category(laptop)
                                        .build();
                        // dell
                        Product lenovo = Product.builder()
                                        .name("Laptop Lenovo Ideapad Slim 5 14IAH8 83BF002NVN")
                                        .description("Laptop Lenovo Ideapad Slim 5 14IAH8 83BF002NVN là một lựa chọn đáng cân nhắc trong phân khúc tầm trung.")
                                        .image("https://res.cloudinary.com/dygjmljlg/image/upload/v1716131273/ucpgutufvfftrzavgtex.jpg")
                                        .price(15190000)
                                        .category(laptop)
                                        .build();
                        // jbl
                        Product jbl = Product.builder()
                                        .name("Tai nghe Bluetooth chụp tai JBL Tune 770NC")
                                        .description("Tai nghe JBL Tune 770NC mang đến một kiểu dáng trẻ trung, kết nối không dây, hỗ trợ trải nghiệm mọi lúc mọi nơi.")
                                        .image("https://res.cloudinary.com/dygjmljlg/image/upload/v1716131362/qxicyxpisyrbjebjx8u1.jpg")
                                        .price(2290000)
                                        .category(sound)
                                        .build();
                        // apple watch
                        Product appleWatch = Product.builder()
                                        .name("Apple Watch SE 2 2023 40mm")
                                        .description("Apple Watch SE 2023 40mm (GPS) với trang bị màn hình Retina cho hiển thị vô cùng sắc nét cùng kính cường lực vô cùng chắc chắn, sang trọng và cực kỳ đẹp mắt.")
                                        .image("https://res.cloudinary.com/dygjmljlg/image/upload/v1716131454/fghg81h5slrv4ta9qflu.jpg")
                                        .price(5690000)
                                        .category(watch)
                                        .build();
                        // logitech
                        Product logitech = Product.builder()
                                        .name("Chuột không dây Logitech M331")
                                        .description("Với công nghệ kết nối Unifying, chuột có thể dùng cho nhiều thiết bị Wireless khác cùng hãng Logitech, chuột không dây Logitech M331 có khả năng kết nối xa lên tới 10m.")
                                        .image("https://res.cloudinary.com/dygjmljlg/image/upload/v1716131557/tarmvjcri3ffbnmmhur1.jpg")
                                        .price(349000)
                                        .category(accessory)
                                        .build();
                        // asus pc
                        Product asus = Product.builder()
                                        .name("PC Asus Rog Strix G10DK-R5600G003W")
                                        .description("PC ASUS ROG Strix G10DK-R5600G003W hiện đang là cái tên được “săn lùng” hiện nay. Liệu PC Gamingnày mang đến sức mạnh mẽ như thế nào mà có thể trở nên nổi tiếng như vậy?")
                                        .image("https://res.cloudinary.com/dygjmljlg/image/upload/v1716131643/b5bolpejtgjkvyg8xvvy.jpg")
                                        .price(18990000)
                                        .category(pc)
                                        .build();
                        // sony tv
                        Product sony = Product.builder()
                                        .name("Smart Tivi Sony 4K 55 inch KD-55X75K")
                                        .description("Tivi Sony 4K 55 inch 55X75K là chiếc Google TV sở hữu vẻ bề ngoài tối giản, sang trọng cùng chất lượng hình ảnh, âm thanh nâng cao để bạn có thể tận hưởng không gian giải trí chất lượng cao ngay tại nhà.")
                                        .image("https://res.cloudinary.com/dygjmljlg/image/upload/v1716131739/gse7o9ew3v5ix06uoa6g.jpg")
                                        .price(12290000)
                                        .category(tv)
                                        .build();
                        // save list
                        List<Product> products = new ArrayList<>(10);
                        products.add(iphone);
                        products.add(samsung);
                        products.add(ipad);
                        products.add(macBook);
                        products.add(lenovo);
                        products.add(jbl);
                        products.add(logitech);
                        products.add(sony);
                        products.add(asus);
                        products.add(appleWatch);
                        this.productRepository.saveAll(products);

                        System.out.println("CREATED BASE PRODUCT DATA");
                }
        }
}

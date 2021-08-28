# BeautyCircle


#### Table of contents

1. [Overview of the enterprise application](#section-1)
2. [Features and use cases](#section-2)
3. [Domain diagram of the system](#section-3)
4. [High level Architecture](#section-4)
5. [Class diagram](#section-5)
6. [Database table](#section-6)
7. [Test case](#section-7)


## 1. Overview of the enterprise application <a name="section-1"></a>
The enterprise application our team chose to develop is an online cosmetics retail website that
supports a hypothetical cosmetics business “Beauty Circle”. The enterprise application supports
the business in selling cosmetics ranging from makeup, skincare, hair products and tools and
accessories. The enterprise consists of two types of users; customer and site administrator
features which have different privilege access to the website. In addition, customers can be
registered users or guest users (who have not registered)
The enterprise application assists Beauty Circle to achieve their business needs through the
following ways.
* Site Administrator can manage resources more efficiently as they can add, update or
delete stock through the website according to the inventory, sales or trends of the
market.
* Database will be linked to the website and store information about customers (registered
users only) and stock information.
* Customers are given more efficient methods to shop as they can view detailed
information of the product, add products into their shopping cart and generate orders by
checking out their shopping cart.
* Registered users can automatically check out through saved cards in their accounts
whereas guest users will be required to enter their payment information and shipping
and payment address.
* All customers paying to the business will pay through an external system that is linked to
the enterprise application.
* Registered users have additional functions compared to guest users as they can view
their account to view their personal information (such as their cards saved and
addresses) and they can also add items into their wishlist to purchase in the future.


## 2. Features and use cases <a name="section-2"></a>

### Feature 1: purchase the cosmetic products.

This feature allows the user to purchase Cosmetic goods from the BeautyCircle website. It allows customers to login/register into their accounts, view and search products they would like to purchase. Customers who are logged into their accounts can add or delete products into the shopping cart, also can add or delete the products they would like to purchase at a later date or items that are currently out of stock into or from their wishlist. The products in wishlist can be added to shopping cart directly. Customers can create the order by checking out their shopping cart automatically.

### Use case

* User log in/register: This service allows users to register or sign into their account. The customer can still view items within their cart after logging out and back into their account again.
* View and search the products: This service allows users to browse the products and read the detailed description and feedback for the product
* Add/delete products into/from the cart: This service allows users to add the products into their shopping cart with desired quantity, and remove any products they no longer want from their shopping cart.
* Create the order: This service allows users to create an order by checking out their shopping cart. Customers can pay through various methods such as credit card or debit cards. The system will verify the validity of the payment and provide users with a receipt and tracking number for the order.
* Add/remove from the wishlist: Users can add products into their wish list and remove from their wishlist themselves or after the items have been purchased by the user

### Feature 2: the Site Administrator manages Beauty Circle

This feature allows the administrator to manage the system. It allows the administrator to login to their designated administrator account. And then the administrator can add or delete products into the database system or create/remove the webpage for the product and view/search specific user’s orders. Administrators can also manage Beauty Circle by viewing and updating web page information such as contact details and product status.

### Use case

* Administrator log in: The system allows the administrator login with their unique identification number to manage the stock within the system.
* View/search the users’ information: The service allows the administrator to search and retrieve users information, and also view and search for orders belonging to each user.
* Update/delete the orders: The service allows the administrator to check the stock availability to process orders, and then update the order status, such as pending, processing, delivering, and complete. It also allows the administrator to delete any order due to any irresistible factors, such as customer ask to cancel, lack of stock. search and retrieve users information, and also be able to view and search for all orders belong to each user.
* Add/delete products into/from the database system: The service allows the administrator to add new items into the database system and remove the items that no longer supplied from the database system.
* Edit product information: The service allows the administrator to edit products’ information, such as name, description and price.
* Create/delete/edit user account: The service allows the administrator to create user accounts for new users, and edit user information, and delete existing user account from the system.
* Create/Edit admin account: The service allows the administrator to create admin accounts for new administrators, and edit administrator account information.


## 3. Domain diagram of the system <a name="section-3"></a>
![image](https://user-images.githubusercontent.com/38289920/131221643-508dbd9b-b368-484f-860d-52567a8e4c7b.png)
### Description:
BeautyCircle is a web based enterprise system that is used by the cosmetics company to sell their products online. The system has two kinds of users, registered user and administrator. The system provides the service for registered user to purchase the cosmetics product based on their needs from a list of available products, and also allow the administrator to manage the system. The system will provide authentication and authorization mechanism to make sure that each user can access the website and act in the system by using their username and password. Order will require the user to register or log in first and the products that have been adding to the shoppingCart and wishList can store to database for user to purchase next time. The RegisteredUser, Order and Product can be managed by AdminUser, as the admin user has the right to add and delete any user, order or product into and from the system. The general overview of domain classes, their main attributes and their relationships are illustrated in the domain class diagram.


## 4. High level Architecture <a name="section-4"></a>
![image](https://user-images.githubusercontent.com/38289920/131221716-a2fc7504-db6c-4631-a885-6aa445e50c87.png)

Based on the system overview, the beautyCircle is presented as a high-level architecture diagram. The high-level architecture of the system is divided into five layers.

![image](https://user-images.githubusercontent.com/38289920/131221737-e12e4226-bfc9-4ba5-a961-31d275dd4393.png)

The component diagram of BeautyCircle is based on high-level architecture, each layer is a subsystem that handle the different aspects of BeautyCircle, and the coupling between each subsystem is low.
* Presentation layer contains view module to display the system and controller module to handle requests.
* Security layer provides authentication, authorisation, validation, data integrity and condentiality to presentation layer.
* Session layer is used to maintain the session state based on client session state.
* Data transfer object (DTO) is created to carry data between processes in order to reduce the number of method calls.
* Remote Facade contains six modules to construct the DTO instance.
* Service layer contains five modules to provide services for users requests.
* Domain logic layer contains the main domain objects and business logic of the system.
* Data mapper layer contains six mapper classes to transfer data between data source layer and domain logic layer.
* Concurrency contain lock managers to control the execution of transactions.
* Data source layer contains the database that is used for persistent data storage.
The high-level architecture model helps the system become more modular and changeable, as the system is divided into different subsystems or components, and each component implement a subset of the overall system's functionality. It improves the mainatainese and understandable of the whole system.


## 5. Class diagram <a name="section-5"></a>
The following diagram shows the class diagrams of each subset of the BeautyCircle system. All of them can compose the class diagram for the whole system.

### Class Diagram for presentation layer
![image](https://user-images.githubusercontent.com/38289920/131221861-3cea6455-8151-4ed4-9168-4e5fe94984dc.png)

### Class Diagram for Domain Logic Layer
![image](https://user-images.githubusercontent.com/38289920/131221872-8a0e3df7-8ea4-4d49-8e23-c8936ef9688c.png)
![image](https://user-images.githubusercontent.com/38289920/131221879-f3d990b9-7b8b-4bb7-be2b-f5d7349da4f5.png)

### Class Diagram for Service Layer
![image](https://user-images.githubusercontent.com/38289920/131221888-5a9c18eb-f9e9-4f77-8272-b3946ed3092b.png)
![image](https://user-images.githubusercontent.com/38289920/131221895-533d2454-2ddb-4656-a9ea-bc697b9e4a9a.png)

### Class Diagram for Data Mapper Layer
![image](https://user-images.githubusercontent.com/38289920/131221912-fdf32e02-7363-4aac-a208-a454809271cb.png)
![image](https://user-images.githubusercontent.com/38289920/131221924-738e482e-09a3-4e2f-8184-52ca8597cb20.png)

### Class Diagram for Concurrency
![image](https://user-images.githubusercontent.com/38289920/131221943-fcdf235d-6c3d-42de-920b-5bb792bd43e7.png)

### Class Diagram for DTO and Remote Facade
![image](https://user-images.githubusercontent.com/38289920/131221973-eabb1ef6-9fe2-4c39-944e-dc821c5eadeb.png)


## 6. Database table <a name="section-6"></a>
![image](https://user-images.githubusercontent.com/38289920/131221986-2672aa0e-13e0-47aa-bfaf-b6813ce75fa5.png)
The database diagram shows all tables with their information and relationships for the database of BeautyCircle system.


## 7. Test case <a name="section-7"></a>
Registered user: 
Username: ksmith 
Password: 123km

Administrator: 
adminLogin: jsmith 
Password: 123jm

Roles:
New users can register the system by themselves. Registered user can view his/her information at profile page, search for the available products after he/she login, add/delete products into/from shopping cart and wishlist. And place the order at the shopping cart page. After the order has been placed, customer can check his order in order page, and also rate the products in the order.
Administrator can update orders’ status, can also help user to cancel the order and edit user’s information. Administrator can manage the product information and user information. Administrator can also create a new administrator or user account.

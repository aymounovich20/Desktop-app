package com.booknet.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.Thread.State;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

import com.booknet.dao.ImpBook;
import com.booknet.dao.ImpCategory;
import com.booknet.dao.ImpUser;
import com.booknet.entities.Book;
import com.booknet.entities.Category;
import com.booknet.util.CategoryModel;
import com.booknet.util.DbConnection;
import com.booknet.util.UserModel;
import com.mysql.jdbc.PreparedStatement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class AdminPanelController implements Initializable {

    @FXML
    private Label lbl_email;
    
    @FXML
    private TableColumn<UserModel, String> col_id;

    @FXML
    private TableColumn<UserModel, String> com_email;

    @FXML
    private TableView<UserModel> tb_users;
    
    @FXML
    private TextField tf_category;
    
    @FXML
    private TableColumn<Category, String> col_category_id;

    @FXML
    private TableColumn<Category, String> col_category_name;

    @FXML
    private TableView<Category> tb_categories;
    
    @FXML
    private Button closeScene;
    @FXML
    private TextField tf_categoryName;
    @FXML
    private TextField tf_categoryId;
    
    @FXML
    private Button bt_upload;

    @FXML
    private ImageView img_book;

    @FXML
    private TextArea tf_BookDescription;

    @FXML
    private TextField tf_BookPrice;

    @FXML
    private TextField tf_BookStock;

    @FXML
    private TextField tf_BookTitle;

    ObservableList<UserModel> oblist = FXCollections.observableArrayList();
    ObservableList<Category> categoryOblist = FXCollections.observableArrayList();
    
    ObservableList<Book> booksList = FXCollections.observableArrayList();
    
    Alert alert = new Alert(Alert.AlertType.ERROR);
    Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
    
    @FXML
    private TableColumn<Book, String> col_book_id;

    @FXML
    private TableColumn<Book, String> col_book_price;

    @FXML
    private TableColumn<Book, String> col_book_stock;

    @FXML
    private TableColumn<Book, String> col_book_title;
    
    @FXML
    private TableView<Book> tb_books;
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	public void userInformation(String email) {
		lbl_email.setText(email);
	}
	public void logOut(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/booknet/fxml/adminLogin.fxml"));
		Parent root = loader.load();
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	public void switchToUsers(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/booknet/fxml/manage-users.fxml"));
		Parent root = loader.load();
		AdminPanelController panelController = loader.getController();
		panelController.userInformation(lbl_email.getText());
		panelController.showUsers();
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("booknet users");
		stage.show();
	}
	public void showUsers() {
		ImpUser impUser = new ImpUser();
		ResultSet rs = impUser.getAllUser();
		
		try {
			while(rs.next()) {
				oblist.add(new UserModel(rs.getInt("id"), rs.getString("email")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
		com_email.setCellValueFactory(new PropertyValueFactory<>("email"));
		tb_users.setItems(oblist);
	}
	
	
	public void switchToBooks(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/booknet/fxml/admin-panel.fxml"));
		Parent root = loader.load();
		AdminPanelController panelController = loader.getController();
		panelController.userInformation(lbl_email.getText());
		panelController.showBooks();
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("logged-in");
		stage.show();
	}
	public void switchToCategory(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/booknet/fxml/manage-categories.fxml"));
		Parent root = loader.load();
		AdminPanelController panelController = loader.getController();
		panelController.userInformation(lbl_email.getText());
		panelController.showCategories(event);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("logged-in");
		stage.show();
	}
	
	public void addNewBook(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/booknet/fxml/add-book.fxml"));
			Parent root = loader.load();
			AdminPanelController controller = loader.getController();
			controller.showListCategories();
			Stage primaryStage = new Stage();
			Scene scene = new Scene(root,520,460);
			primaryStage.setScene(scene);
			primaryStage.setTitle("BookNet | Add Book");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	FileChooser fileChooser = new FileChooser();
	File file1 ;
	public void readImage(ActionEvent event) throws FileNotFoundException {
		
		File file = fileChooser.showOpenDialog(bt_upload.getScene().getWindow());
		file1=file;
		FileInputStream inputStream = new FileInputStream(file);
		Image image = new Image(inputStream);
		img_book.setImage(image);
	}
	public void addBook(ActionEvent event) throws FileNotFoundException {
		Connection conn = DbConnection.openConnection();
		try {
			FileInputStream inputStream = new FileInputStream(file1);
			//Statement stmt = conn.createStatement();
			java.sql.PreparedStatement store;
			String storeStatement = "INSERT INTO book(title,description,price,stock,idCategory,image) VALUES(?,?,?,?,?,?)";
			store = conn.prepareStatement(storeStatement);
			store.setString(1, tf_BookTitle.getText());
			store.setString(2, tf_BookDescription.getText());
			store.setInt(3, Integer.valueOf(tf_BookPrice.getText()));
			store.setInt(4, 10);
			ImpCategory impCategory = new ImpCategory();
			store.setInt(5, impCategory.findCategoryIdByName(ch_cat.getValue()));
			//System.out.println(select_category.getItems().add);
			System.out.println(impCategory.findCategoryIdByName(ch_cat.getValue()));
			store.setBinaryStream(6, inputStream);
			store.execute();
			
			//int qq = stmt.executeUpdate("INSERT INTO book(image) VALUES("+inputStream+")");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//img_book.
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("book added successfully");
		alert.show();
	}
	@FXML
    private ChoiceBox<String> ch_cat;
	public void showListCategories() {
		//select_category.getItems().add(new MenuItem("hi"));
		ImpCategory impCategory = new ImpCategory();
		ResultSet rs = impCategory.getAllCategories();

		try {
			while(rs.next()) {
				//select_category.getItems().add(new MenuItem(String.rs.getString("name")));
				
				ch_cat.getItems().add(rs.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void showBooks() {
		ImpBook impBook = new ImpBook();
		ResultSet rs = impBook.allBooks();
		try {
			while(rs.next()) {
				booksList.add(new Book(rs.getInt("id"),rs.getString("title"),rs.getFloat("price"),rs.getInt("stock")));
				System.out.println(rs.getInt("id"));
				//booksList.size();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		col_book_id.setCellValueFactory(new PropertyValueFactory<>("id"));
		col_book_title.setCellValueFactory(new PropertyValueFactory<>("title"));
		col_book_price.setCellValueFactory(new PropertyValueFactory<>("price"));
		col_book_stock.setCellValueFactory(new PropertyValueFactory<>("stock"));
		tb_books.setItems(booksList);
		System.out.println(booksList.size());
	}
	
	public void addNewCategory(ActionEvent event) {
		try {
			Pane root = FXMLLoader.load(getClass().getResource("/com/booknet/fxml/add-category.fxml"));
			Scene scene = new Scene(root,520,460);
			Stage primaryStage = new Stage();
			primaryStage.setScene(scene);
			
			primaryStage.setTitle("BookNet | Add Category");
			//bt_updateCategory.setVisible(true);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void addCategory(ActionEvent event) throws SQLException {
		ImpCategory impCategory = new ImpCategory();
		ResultSet rs = impCategory.findByName(tf_category.getText());
		if(rs.isBeforeFirst()) {
			System.out.println("category already exists!");
			alert.setContentText("Category already exists!");
			alert.show();
		}else {
			impCategory.addCategory(new Category(tf_category.getText()));
			alert1.setContentText("Category added successfully !");
			alert1.show();
		}
	}
	public void showCategories(ActionEvent event) {
		ImpCategory impCategory = new ImpCategory();
		ResultSet rs = impCategory.getAllCategories();
		try {
			while(rs.next()) {
				categoryOblist.add(new Category(rs.getInt("id"),rs.getString("name")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		col_category_id.setCellValueFactory(new PropertyValueFactory<>("id"));
		col_category_name.setCellValueFactory(new PropertyValueFactory<>("name"));
		tb_categories.setItems(categoryOblist);
	}
	public void removeCategory(ActionEvent event) {
		System.out.println("you clicked category delete button");
		Category category = tb_categories.getSelectionModel().getSelectedItem();
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		if(category==null) {
			alert.setAlertType(AlertType.ERROR);
			alert.setContentText("select a category first");
			alert.show();
		}else {
		tb_categories.getItems().removeAll(tb_categories.getSelectionModel().getSelectedItem());
		System.out.println(category.getName());
		ImpCategory impCategory = new ImpCategory();
		impCategory.deleteCategoryByName(category.getName());
		alert.setContentText("Category deleted");
		alert.show();
		}
	}
	public void closeScene(ActionEvent event) {
		Stage stage = (Stage) closeScene.getScene().getWindow();
		stage.close();
	}
	public void updateCategory(ActionEvent event) {
		try {
			Category category = tb_categories.getSelectionModel().getSelectedItem();
			Alert alert = new Alert(Alert.AlertType.ERROR);
			if(category==null) {
				alert.setContentText("select a category !");
				alert.show();
			}else {
			System.out.println(category.getName());
//			Pane root = FXMLLoader.load(getClass().getResource("/com/booknet/fxml/update-category.fxml"));
//			Scene scene = new Scene(root,520,460);
			//tf_categoryName.setText("hello");
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/booknet/fxml/update-category.fxml"));
			Parent root = loader.load();
			AdminPanelController controller = loader.getController();
			controller.showCategoryToUpdate(category.getName(),String.valueOf(category.getId()));
			Stage primaryStage = new Stage();
			Scene scene = new Scene(root,520,460);
			primaryStage.setScene(scene);
			primaryStage.setTitle("BookNet | Update Category");
			//bt_updateCategory.setVisible(true);
			primaryStage.show();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void showCategoryToUpdate(String name,String id) {
		tf_categoryName.setText(name);
		tf_categoryId.setEditable(false);
		tf_categoryId.setText(id);
	}
	public void updateNewCategory(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		System.out.println(tf_categoryName.getText());
		if(tf_categoryName.getText().length()>0) {
			ImpCategory impCategory = new ImpCategory();
			impCategory.updateCategory(Integer.valueOf(tf_categoryId.getText()), tf_categoryName.getText());
			alert.setContentText("Category updated successful");
			alert.show();
		}else {
			alert.setAlertType(AlertType.ERROR);
			alert.setContentText("Category name should'nt be empty!");
			alert.show();
		}
	}
}

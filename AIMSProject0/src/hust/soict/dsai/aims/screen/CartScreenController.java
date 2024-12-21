package hust.soict.dsai.aims.screen;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import hust.soict.dsai.aims.media.*;
import hust.soict.dsai.aims.store.Store;
import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.exception.PlayerException;

public class CartScreenController {

    @FXML
    private Button btnPlay;

    @FXML
    private Button btnRemove;

    @FXML
    private TableColumn<Media, Float> colMediaCost;

    @FXML
    private TableColumn<Media, String> colMediaTitle;

    @FXML
    private TableColumn<Media, String> colMediacategory;

    @FXML
    private ToggleGroup filterCategory;

    @FXML
    private TableView<Media> tblMedia;

    @FXML
    private RadioButton radioBtnFilterId;

    @FXML
    private RadioButton radioBtnFilterTitle;

    @FXML
    private TextField tfFilter;

    private Cart cart;
    private Store store;

    public CartScreenController(Cart cart, Store store) {
        super();
        this.cart = cart;
        this.store = store;
    }

    @FXML
    private void initialize() {
    	
        colMediaTitle.setCellValueFactory(new PropertyValueFactory<Media, String>("title"));
        colMediacategory.setCellValueFactory(new PropertyValueFactory<Media, String>("category"));
        colMediaCost.setCellValueFactory(new PropertyValueFactory<Media, Float>("cost"));
        tblMedia.setItems(this.cart.getItemsOrdered());
        
        btnPlay.setVisible(false);
        btnRemove.setVisible(false);

        tblMedia.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Media>() {
                	
                    @Override
                    public void changed(ObservableValue<? extends Media> observable, Media oldValue, Media newValue) {
                        if (newValue != null) {
                            updateButtonBar(newValue);
                        }
                    }
                });
        
        tfFilter.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                showFilteredMedia(newValue);
            }
        });
        tblMedia.setItems(this.cart.getItemsOrdered());
        updateTotal();
    }

    private void updateButtonBar(Media media) {
        if (media == null) {
            btnRemove.setVisible(false);
            btnPlay.setVisible(false);
        } else {
            btnRemove.setVisible(true);
            if (media instanceof Playable) {
                btnPlay.setVisible(true);
            } else {
                btnPlay.setVisible(false);
            }
        }
    }
    
    @FXML
    void btnplaypressed(ActionEvent event) {
       Media media = this.tblMedia.getSelectionModel().getSelectedItem();
		try {
			((Playable)media).play();
		} catch (PlayerException e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Media Player");
			alert.setHeaderText("ERROR: Media length is non-positive.");
			alert.setContentText("Media cannot be played.");
			alert.showAndWait();
		}
    }

    private void showFilteredMedia(String filterText) {
        String filterType = "";
        if (radioBtnFilterTitle.isSelected()) {
            filterType = "title";
        } else {
            filterType = "id";
        }
        if (filterText == null || filterText.isEmpty()) {
            tblMedia.setItems(cart.getItemsOrdered());
            return;
        }
        ObservableList<Media> filteredMedia = cart.filterMedia(filterText, filterType);
        tblMedia.setItems(filteredMedia);
    }

    @FXML
    void btnRemovePressed(ActionEvent event) {
        Media media = tblMedia.getSelectionModel().getSelectedItem();
        if (media != null) {
            if (cart.getItemsOrdered().contains(media)) {
                cart.removeMedia(media); 
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Notification");
                alert.setHeaderText("Failed to remove");
                alert.setContentText("Media not found in the cart.");
                alert.showAndWait();
            }

            String filterText = tfFilter.getText();
            if (filterText == null || filterText.isEmpty()) {
                tblMedia.setItems(cart.getItemsOrdered());
                updateTotal();
                return;
            }

            String filterType = radioBtnFilterTitle.isSelected() ? "title" : "id";
            ObservableList<Media> filteredMedia = cart.filterMedia(filterText, filterType);
            tblMedia.setItems(filteredMedia);
        } else {
            System.out.println("No media selected to remove");
        }

        updateTotal();
    }


    
    @FXML
    void placeorderbutton(ActionEvent event) {
        cart.getItemsOrdered().clear();
        updateTotal();
        tblMedia.setItems(cart.getItemsOrdered());
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(
                javafx.scene.control.Alert.AlertType.INFORMATION);
        alert.setTitle("Order Confirmation");
        alert.setHeaderText("Thank you!");
        alert.setContentText("Your order has been placed successfully!");
        alert.showAndWait();

    }

    @FXML
    private Label total;

    public void updateTotal() {
        if (total != null) {
            float totalCost = cart.totalCost();
            total.setText(String.format("Total: $%.2f", totalCost));
        }
    }

    @FXML
    void viewstore(ActionEvent event) {
        StoreScreen storeScreen = new StoreScreen(store, cart);
        storeScreen.setVisible(true);
        updateTotal();
        
    }
}
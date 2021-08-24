package model.hud;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

import com.sun.javafx.collections.MappingChange.Map;

import Utilities.Parameters;
import controller.gameEventController.GameEventController;
import controller.status.StatusController;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.status.Status;
import model.status.StatusEnum;
import model.status.StatusFactory;
import view.gameField.GameField;

public class HUDBonusImpl implements IHUDBonus {
   
   private TreeSet<StatusEnum> statusLoaded;
   private GameField gameField;
   private List<StatusEnum> activeStatus;
   private HashMap<StatusEnum, Image> imageMap;
   private StatusFactory statusFactory;
   
   public HUDBonusImpl(final GameField gameField) {
       
       this.gameField = gameField;
       this.activeStatus = this.gameField.getStatusController().getActiveStatus();
       this.imageMap = new HashMap<>();
       
       this.imageMap.put(StatusEnum.BonusSpeed, Parameters.bonusSpeedImage);  
       this.imageMap.put(StatusEnum.BonusLife, Parameters.bonusSpeedImage);  
       this.imageMap.put(StatusEnum.MalusCommand, Parameters.bonusSpeedImage);  
       this.imageMap.put(StatusEnum.MalusSpeed, Parameters.bonusSpeedImage);  
       this.imageMap.put(StatusEnum.MalusFire, Parameters.bonusSpeedImage);  
     
   }

   @Override
   public void loadStatus(final StatusEnum statusToLoad) {
       this.statusLoaded.add(statusToLoad);
       
       int spacing = 10;

        
     //  this.gameField.getBonusObjects().add(statusFactory.createStatus(statusToLoad));


       
      /*  for(StatusEnum elem :  )
       
       this.loadedImages[statusLoaded.size() ] = new ImageView(this.imageToBeLoaded.get(statusToLoad.ordinal()));
        this.loadedImages[statusLoaded.size() ].setLayoutX(HUDPointsImpl.FIVE);
       this.loadedImages[statusLoaded.size() ].setLayoutY(10 * this.statusLoaded.size() );
       this.gameField.getGameContainer().getChildren().add(this.loadedImages[statusLoaded.size() /]);
       this.loadedImages[statusLoaded.size() ].setViewOrder(GameEventController.VIEW_ORDER); 
   } */
   
   //metodo per scaricare risorse
   
   //check risorse
}
}

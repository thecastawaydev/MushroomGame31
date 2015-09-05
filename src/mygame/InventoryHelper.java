/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.collision.CollisionResult;
import com.jme3.collision.CollisionResults;
import com.jme3.math.Ray;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Spatial;
import tonegod.gui.controls.buttons.ButtonAdapter;

/**
 *
 * @author christian
 */
public class InventoryHelper {

    public static void AddToInventory(Spatial itemToAdd, int quantityToAdd){
        for(int i = 0; i < 40; i++){
            if(Main.inventory.inventorySlots[i].itemName.equals(itemToAdd.getName())){
                Main.inventory.inventorySlots[i].quantity += 1;
                Main.inventory.inventoryElements[i].setToolTipText(itemToAdd.getName() +  " : " + Main.inventory.inventorySlots[i].slotNumber + " : " + Main.inventory.inventorySlots[i].quantity);
                break;
            }
            else if(Main.inventory.inventorySlots[i].itemName.equals("empty")){
                Main.inventory.inventorySlots[i].setName(itemToAdd.getName());
                Main.inventory.inventorySlots[i].setQuantity(quantityToAdd);
                Main.inventory.inventoryElements[i].setToolTipText(itemToAdd.getName() +  " : " + Main.inventory.inventorySlots[i].slotNumber + " : " + Main.inventory.inventorySlots[i].quantity);
                Main.inventory.inventoryElements[i].setButtonIcon( 40, 40, "Textures/" + itemToAdd.getName() + "icon.png");
                break;
            } 
            
        }
    }
    
    public static void AddToInventory(String itemName, int quantityToAdd){
        for(int i = 0; i < 40; i++){
            if(Main.inventory.inventorySlots[i].itemName.equals(itemName)){
                Main.inventory.inventorySlots[i].quantity += 1;
                Main.inventory.inventoryElements[i].setToolTipText(itemName +  " : " + Main.inventory.inventorySlots[i].slotNumber + " : " + Main.inventory.inventorySlots[i].quantity);
                break;
            }
            else if(Main.inventory.inventorySlots[i].itemName.equals("empty")){
                Main.inventory.inventorySlots[i].setName(itemName);
                Main.inventory.inventorySlots[i].setQuantity(quantityToAdd);
                Main.inventory.inventoryElements[i].setToolTipText(itemName +  " : " + Main.inventory.inventorySlots[i].slotNumber + " : " + Main.inventory.inventorySlots[i].quantity);
                Main.inventory.inventoryElements[i].setButtonIcon( 40, 40, "Textures/" + itemName + "icon.png");
                break;
            } 
            
        }
    }
    
    public static void RemoveFromInventory(Camera cam, Spatial model, InventorySlot[] inventorySlots, ButtonAdapter[] inventoryElements, int index){
        Statics.s_ShowInventory = false;
            CollisionResults results = new CollisionResults();
            Ray ray = new Ray();
            Vector2f click2d = Main.s_InputManager.getCursorPosition();
            Vector3f click3d = cam.getWorldCoordinates(new Vector2f(click2d.getX(), click2d.getY()), 0f);
            Vector3f dir = cam.getWorldCoordinates(new Vector2f(click2d.getX(), click2d.getY()), 1f).subtractLocal(click3d).normalizeLocal();
            ray.setOrigin(click3d);
            ray.setDirection(dir);
             ray = new Ray(click3d, dir);
            model.collideWith(ray, results);
            
            if(results.size() > 0){
                if(Statics.s_PlayerSettingModel == false){
                
                CollisionResult point = results.getClosestCollision();
                Vector3f destination = point.getContactPoint();

                 ObjectHelper.s_Model = ObjectHelper.AddModel(destination, inventorySlots[index].itemName);
                
                Main.s_TreeNode.attachChild(ObjectHelper.s_Model);
                inventorySlots[index].quantity--;
                inventoryElements[index].setToolTipText(inventorySlots[index].itemName + " : " + inventorySlots[index].slotNumber + " : " + inventorySlots[index].quantity);
                if(inventorySlots[index].quantity <= 0){
                    inventorySlots[index].itemName = "empty";
                    inventoryElements[index].setToolTipText(inventorySlots[index].itemName + " : " + inventorySlots[index].slotNumber + " : " + inventorySlots[index].quantity);
                    inventoryElements[index].removeChild(inventoryElements[index].getButtonIcon());
                    
                }
                Statics.s_PlayerSettingModel = true;
                           
                            
                }
                }
    }
}

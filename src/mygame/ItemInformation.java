/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.bullet.collision.shapes.CapsuleCollisionShape;
import java.util.HashMap;

/**
 *
 * @author christian
 */
public class ItemInformation {
    
    public static HashMap<String, Item> table;
    public static HashMap<String, String> itemOutput;
    
    public static void InitItemInfo()
    {
        table = new HashMap<String, Item>();
        itemOutput = new HashMap<String, String>();
        setUpTree();
        setUpRock();
        setUpLog();
        setUpPebble();
    }
    
    private static void setUpTree(){
        table.put("tree", new Item(0.8f, new CapsuleCollisionShape(0.5f, 1), 50));
    }
    
    private static void setUpRock(){
        table.put("rock", new Item(1f, new CapsuleCollisionShape(0.5f, 1), 60));
        
    }
    
    private static void setUpLog(){
        table.put("log", new Item(1f, new CapsuleCollisionShape(0.5f, 1), 30));
        itemOutput.put("tree", "log");
    }
    private static void setUpPebble(){
        table.put("pebble", new Item(1f, new CapsuleCollisionShape(0.5f, 1), 30));
        itemOutput.put("rock", "pebble");
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.CameraNode;
import com.jme3.scene.Node;
import com.jme3.scene.control.CameraControl;

/**
 *
 * @author christian
 */
public class ThirdPersonCamera {
    
    private Node pivot;
    private CameraNode cameraNode;
    
    public float followDistance = 7;
    public float verticalAngle = 30 * FastMath.DEG_TO_RAD;
    
    public float maxVerticalAngle = 85 * FastMath.DEG_TO_RAD;
    public float minVerticalAngle = 5 * FastMath.DEG_TO_RAD;
    
    public ThirdPersonCamera(String name, Camera cam, Node player)
    {
        pivot = new Node("CamTrack");
        player.attachChild(pivot);
        
        cameraNode = new CameraNode(name, cam);
        cameraNode.setControlDir(CameraControl.ControlDirection.SpatialToCamera);
        pivot.attachChild(cameraNode);
        cameraNode.setLocalTranslation(new Vector3f(0, 0, followDistance));
        cameraNode.lookAt(pivot.getLocalTranslation(), Vector3f.UNIT_Y);
        
        pivot.getLocalRotation().fromAngleAxis(-verticalAngle, Vector3f.UNIT_X);
    }
    
    public void verticalRotate(float angle)
    {
        verticalAngle += angle;
        
        if(verticalAngle > maxVerticalAngle)
        {
            verticalAngle = maxVerticalAngle;
        }
        else if(verticalAngle < minVerticalAngle)
        {
            verticalAngle = minVerticalAngle;
        }
        
        pivot.getLocalRotation().fromAngleAxis(-verticalAngle, Vector3f.UNIT_X);
    }
    
    public CameraNode getCameraNode()
    {
        return cameraNode;
    }
    
    public Node getCameraTrack()
    {
        return pivot;
    }
}

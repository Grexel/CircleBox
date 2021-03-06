/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland.Monsters;

import circleland.CircleAttack;
import circleland.CircleEntity;
import circleland.CircleLandUtility;
import circleland.CircleMap;
import circleland.CircleMonster;
import circleland.Weapons.BombWeapon;
import circleland.Weapons.BulletWeapon;
import circleland.Weapons.RapierWeapon;
import java.awt.Color;

/**
 *
 * @author Jeff
 */
public class InchWormMonster extends CircleMonster{
    public static final int BASE_MAXHEALTH= 10;
    public static final int BASE_HEALTHREGEN= 1;
    public static final int BASE_MAXMANA= 10;
    public static final int BASE_MANAREGEN= 10;
    public static final int BASE_ATTACK_DAMAGE= 1;
    public static final int BASE_MAGIC_DAMAGE= 1;
    public static final int BASE_ATTACK_DEFENSE= 2;
    public static final int BASE_MAGIC_DEFENSE= 1;
    public static final int BASE_PRECISION = 1;
    public static final int BASE_ATTACKSPEED= 1000;
    public static final int BASE_CASTSPEED= 1000;
    public static final int BASE_MOVESPEED= 80;
    
    private int viewRadius;
    private int moveTimer;
    public InchWormMonster()
    {
        super();
        name = "Inch Worm";
        size = 10;
        headRadius = 0;
        headSize = 0;
        color = Color.GRAY;
        viewRadius = 200;
        
        //initialize stats
        team = CircleLandUtility.MONSTER_SIDE;
        level = 5;
        maxHealth = 50;
        health = 50;
        attackSpeed = 1000;
        castSpeed = 100;
        moveSpeed = 100;
        moveTimer = 0;
        gold = 5;
        equippedWeapon = new RapierWeapon();
        equippedWeapon.bulletSize(40);
    }
    public InchWormMonster(int x, int y)
    {
        super();
        name = "Inch Worm";
        size = 10;
        headRadius = 0;
        headSize = 0;
        color = Color.GRAY;
        viewRadius = 200;
        position.x = x;
        position.y = y;
        
        //initialize stats
        team = CircleLandUtility.MONSTER_SIDE;
        level = 5;
        maxHealth = 50;
        health = 50;
        attackSpeed = 1000;
        castSpeed = 100;
        moveSpeed = 100;
        moveTimer = 0;
        gold = 5;
        equippedWeapon = new RapierWeapon();
        equippedWeapon.bulletSize(40);
    }
    public void update(long deltaTime, CircleMap world) {
        //Go to focusedEntity
        if(focusedEntity != null)
        {
            //moveLeft and right
            if (focusedEntity.position().x > position.x + 10){
                moveRight = true;
                moveLeft = false;
            } else if(focusedEntity.position().x < position.x -10) {
                moveLeft = true;
                moveRight = false;
            } else {
                moveLeft = false;
                moveRight = false;
            }
            //moveUp and down
            if (focusedEntity.position().y > position.y + 10){
                moveDown = true;
                moveUp = false;
            } else if(focusedEntity.position().y < position.y -10) {
                moveUp = true;
                moveDown = false;
            } else {
                moveUp = false;
                moveDown = false;
            }
            if(moveTimer >= 0){
                moveUp = false;
                moveDown = false;
                moveLeft = false;
                moveRight = false;
            }
            //face toward focused entity
            aim.x = focusedEntity.position().x;
            aim.y = focusedEntity.position().y;
            doAttack = true;
            if(distanceBetweenPoints(focusedEntity.position(), position) > viewRadius)
            {
                focusedEntity = null;
                doAttack = false;
            }
        } 
        else {
            moveLeft = false;
            moveRight = false;
            moveUp = false;
            moveDown = false;
            doAttack = false;
            for(CircleEntity cE : world.players()) {
                if(cE.intersectsCircle(position, viewRadius))
                {
                    //this is our new focus
                    focusedEntity = cE;
                    break;
                }
            }
        }
        moveTimer -= deltaTime;
        super.update(deltaTime, world);
        //debug
        //System.out.println("Position: " + position.x + "," + position.y);
    }
    
    public void attack(CircleMap world){
            super.attack(world);
            moveTimer = attackTimer/2;
    }
}

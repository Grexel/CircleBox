/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland.Skills.NecromancerSkills;

import circleland.Attacks.NecromancerAttacks.LeechLifeCurseSkillAttack;
import circleland.Attacks.NecromancerAttacks.WeakenCurseSkillAttack;
import circleland.CircleAttack;
import circleland.CircleEntity;
import circleland.CircleSkill;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 *
 * @author Jeff
 */
public class LeechLifeCurseSkill extends CircleSkill {
    public static final Color CENTER_COLOR = Color.RED; //item type color
    private static final Color CURSEFIELD_COLOR = new Color(0xFF,0x00,0x20,0x88);
    public LeechLifeCurseSkill()
    {
        super();
        name = "Leech Life";
        skillLevel = 10;
    }
    public void attack(CircleEntity owner, ArrayList<CircleAttack> attacks)
    {
        owner.mana(owner.mana() - manaCost);
        double velX = 0;
        double velY = 0;
        double curseSize = 100 + skillLevel * 20;
        castLife = 10000 + skillLevel * 5000;
        LeechLifeCurseSkillAttack bA = new LeechLifeCurseSkillAttack(owner,castLife,0,
            owner.aim().x,owner.aim().y,velX,velY,(int)curseSize,CURSEFIELD_COLOR,skillLevel);
        attacks.add(bA);
    }
    @Override
    public void addBonus(CircleEntity circle) {
        circle.minMagicDamage(1);
        circle.maxMagicDamage(this.skillLevel);
    }
    public void draw(Graphics2D graphics, int x, int y)
    {
        graphics.setStroke(new BasicStroke(4));
        graphics.setColor(Color.RED);
        graphics.fillOval((int)(x - (size+4)/ 2), (int)(y - (size+4) / 2),
            (int)size+4, (int)size+4);
        graphics.setColor(Color.BLACK);
        graphics.drawLine(x, y-(int)size/4, x, y+(int)size/4);
        graphics.drawLine(x+(int)size/4, y, x-(int)size/4, y);
    }
    
    
}
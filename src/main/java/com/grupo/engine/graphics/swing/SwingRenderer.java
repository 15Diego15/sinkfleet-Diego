package com.grupo.engine.graphics.swing;

import com.grupo.engine.core.Blackboard;
import com.grupo.engine.core.ResizeListener;
import com.grupo.engine.entities.Entity;
import com.grupo.engine.graphics.RenderAPI;
import com.grupo.game.core.BlackBoard2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public abstract class SwingRenderer extends JPanel implements RenderAPI {

    public SwingRenderer(int width, int height, ResizeListener resizeListener) {
        setPreferredSize(new Dimension(width, height));
        setDoubleBuffered(true);
        setFocusable(true);

        
            addKeyListener(BlackBoard2.currentPlayer.getKeyboardManager());
        

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                resizeListener.onResize(e.getComponent().getWidth(), e.getComponent().getHeight());
            }
        });
    }



    @Override
    public void render() {
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        drawBackground(g2);
        Entity[] entities = Blackboard.entityManager.getEntities();
        for (int i = 0; i < Blackboard.entityManager.getNumEntities(); i++) {
            drawEntity(g2, entities[i]);
        }
    }

    public abstract void drawEntity(Graphics2D g2, Entity e);
    public abstract void drawBackground(Graphics2D g2);
}

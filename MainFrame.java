package treningowe;

import java.awt.*;
import java.awt.geom.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class MainFrame extends JFrame implements Runnable {

    private JPanel buttonPanel;
    private MyPanel drawPanel;
    private ButtonGroup group;
    
    
    public MainFrame(String title){

        super(title);

        Dimension dim= Toolkit.getDefaultToolkit().getScreenSize();
        setSize(dim.width / 2,dim.height / 2);
        setLocationByPlatform(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setLayout(new FlowLayout());
        
        //Tworze przyciski
        JTextField Xstart = new JTextField("Podaj X start",10);
        JTextField Ystart = new JTextField("Podaj Y start",10);
        JTextField Xend = new JTextField("Podaj X end",10);
        JTextField Yend = new JTextField("Podaj Y end",10);

        JRadioButton blueButton = new JRadioButton("Niebieski");
        JRadioButton redButton = new JRadioButton("Czerwony");
        
        //Dodawanie buttonow do ButtonGroup
        ButtonGroup bGroup = new ButtonGroup();
        bGroup.add(blueButton);
        bGroup.add(redButton);

        JButton rysButton = new JButton("Rysuj");

        buttonPanel = new JPanel();
        drawPanel = new MyPanel(/*0.0,0.0,0.0,0.0*/);

        //dodawanie przycisków do panelu
        buttonPanel.add(Xstart);
        buttonPanel.add(Ystart);
        buttonPanel.add(Xend);
        buttonPanel.add(Yend);

        buttonPanel.add(blueButton);
        buttonPanel.add(redButton);

        buttonPanel.add(rysButton);


        rysButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                drawPanel.xstart = Double.parseDouble(Xstart.getText().trim());
                drawPanel.ystart = Double.parseDouble(Ystart.getText().trim());
                drawPanel.xend = Double.parseDouble(Xend.getText().trim());
                drawPanel.yend = Double.parseDouble(Yend.getText().trim());

                if(redButton.isSelected()){
                    drawPanel.color = Color.RED;
                }
                else if(blueButton.isSelected()){
                    drawPanel.color = Color.BLUE;
                }

                drawPanel.check = true;
                repaint();
            };
            
        });


        //dodanie panelu do ramki
        add(buttonPanel);
        add(drawPanel);

    }

    private class MyPanel extends JPanel{

        private boolean check;
        private double xstart;
        private double ystart;
        private double xend;
        private double yend;
        
        private Color color;

        public MyPanel(/*Double xs, Double ys, Double xe, Double ye*/){
            check = false;
            color = Color.BLACK;
            /*xstart = xs;
            ystart = ys;
            xend = xe;
            yend = ye;*/
        }

        public void paintComponent(Graphics g){
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            if(check){
                g2.setPaint(color);
                g2.draw(new Line2D.Double(xstart,ystart,xend,yend));
            }
        }

        public Dimension getPreferredSize(){
            return new Dimension(200, 200);
        }

    }

    /*private class RysowanieAction implements ActionListener{

        public void actionPerformed(ActionEvent event){
            drawPanel.check = true;
            repaint();
        }
    }*/
/*
    private class WspXstart implements ActionListener{

        public void actionPerformed(ActionEvent event){
            String text = xstart.getText().trim();
            drawPanel.xstart = Double.parseDouble(text);
        }
    }*/

    @Override
    public void run(){
        setVisible(true);
    }
}
package tanchishe;


import java.util.Random;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class snake extends Application{
	Pane pane;
	Scene scene;
	Timeline tl;
	private int x=0,weigth=30;
	private int y=0,heigth=30;
	 int wcount=0,hcount=0;
	private Color color;
	int west=0,south=1,north=0,east=0;
	int count=1,length=15;
	int mapx=0,mapy=0;
	double [][]map=new double[24][24];
	Random random=new Random();
	 int istwo=0;
	 int a,b,score=0,second=1;
	 public void start(Stage primaryStage){
		 for(int i=0;i<24;i++){
	   		  for(int j=0;j<24;j++){
	   		  map[i][j]=0;
	   		  }
	   		  }
		 pane=new Pane();
		 Handlef hhhh=new Handlef();
	    	pane.setOnMouseDragged(hhhh);
		       color=new Color(0.20,0.90,0.60,0.70);
//		       Rectangle r=new Rectangle(x,y,30,30);
//		    	r.setFill(color);
//		    	r.setStroke(Color.GREEN);
//		    	 pane.getChildren().add(r);
//=================================时间轴=========================================
			    handlee hh=new handlee();
			   tl=new Timeline(new KeyFrame(Duration.millis(500),hh));
			   tl.setCycleCount(Timeline.INDEFINITE);
		       tl.play();
//=================================键盘事件=========================================	    	
		    	 handle hhh=new handle();
		    	  pane.setOnKeyPressed(hhh);
		    	scene=new Scene(pane,690,690);
		 		primaryStage.setTitle("tanchishe");
		 		primaryStage.setScene(scene);
		 		primaryStage.setResizable(false);
		 		primaryStage.show();
		 		pane.requestFocus();
	   
	 }
//================================时间抽处理事件类==========================================
	    class handlee implements EventHandler<ActionEvent>{
			public void handle(ActionEvent e){	
				Map();
				randomm();
				xingzou();
				
				if(wcount==a&&hcount==b){
					tl.setRate(tl.getRate()+0.1);
				}
				
				pengzhuang();
				chujie();				
				 System.out.println("wcount:"+wcount+"hcount:"+hcount);
//				 System.out.println("a:"+a+"b:"+b);
//				 
//				 System.out.println("second:"+second);
				 
			}
			}
//================================键盘处理事件类========================================
	    class handle implements EventHandler<KeyEvent>{
	   	 public void handle(KeyEvent e){
	   		if(e.getCode()==KeyCode.UP){
	   	   		west=0;
	   	   	    south=0;
	   	   		north=1;
	   	   		east=0;
	   	   	 }
	   	   	if(e.getCode()==KeyCode.DOWN){
	   	   		west=0;
	   	   	    south=1;
	   	   		north=0;
	   	   		east=0;
	   	   	 }
	   	   	if(e.getCode()==KeyCode.LEFT){
	   	   		west=1;
	   	   	    south=0;
	   	   		north=0;
	   	   		east=0;
	   	   	 }
	   	   	if(e.getCode()==KeyCode.RIGHT){
	   	   		west=0;
	   	   	    south=0;
	   	   		north=0;
	   	   		east=1;
	   	   	 }
	   	 }
	   	 }
//==================================鼠标事件========================================
	    class Handlef implements EventHandler<MouseEvent>{
			public void handle(MouseEvent e){
				if(e.getButton()==MouseButton.PRIMARY){
					tl.pause();
				}
				if(e.getButton()==MouseButton.SECONDARY){					
					 tl.play();					 
				}
			}
			}	
//=================================指定走向函数=======================================	    
	    public void fangxiang(int south,int west,int north,int east){
	    	this.south=south;
	    	this.west=west;
	    	this.north=north;
	    	this.east=east;
	    }
//============================判断出界函数============================================
	    public void chujie(){
	    	if(hcount>22||wcount>22||hcount<0||wcount<0){
    			tl.stop();
    			//pane.getChildren().clear();
    		}   		
	    }
//===================================地图函数===================================	 
	    public void Map(){
	    	mapy=hcount;
			mapx=wcount;
			map[mapx][mapy]=1;
			if(count>length){
				 Rectangle rr=(Rectangle) pane.getChildren().get(0);
				 map[(int)rr.getX()/30][(int)rr.getY()/30]=0;	
				 Rectangle rrr=(Rectangle) pane.getChildren().get(1);
				 map[(int)rrr.getX()/30][(int)rrr.getY()/30]=0;
//				 if(second<length||second==length){
//					 if(wcount==a&&hcount==b){
//						 System.out.println("haha");
//						 tl.play();
//					 }
//				 }
				 
			}
	    }
//===================================正常行走函数============================================
	    public void xingzou(){
	    	
	    	if(south==1){
	    		hcount+=1;
	    	      xingzouzihanshu();
	    	}
	    	if(north==1){
	    		hcount-=1;
	    	      xingzouzihanshu();
	    	}
	    	if(west==1){
	    		wcount-=1;
	    	      xingzouzihanshu();
	    	}
	    	if(east==1){
	    		wcount+=1;
	    	      xingzouzihanshu();
	    	}
	    	
	    }
//=========================随机生成函数==========================================
	    public void randomm(){   	
			   if(istwo==0){
				   while(true){
					   a=random.nextInt(23);
					   b=random.nextInt(23);  
					   if(map[a][b]==0){
						   break;
					   }
				   }  
				   Rectangle rrr=new Rectangle(a*30,b*30,30,30);
					  pane.getChildren().add(rrr);
					  istwo=1;
				   
			   }
	    }
//=============================行走子函数=========================================
	    public void xingzouzihanshu(){
	    	chujie();
	    	count+=1;
	    	second+=1;
	    	 Rectangle r=new Rectangle(x+weigth*wcount,y+heigth*hcount,30,30);
		        r.setFill(color);
		        r.setStroke(Color.GREEN);
		        
	    	              if(count>length){
	    	            	  Rectangle rr=(Rectangle) pane.getChildren().get(0);
	    	            	            if(wcount==a&&hcount==b){
	    	            	            	 pane.getChildren().remove(0);
	    	            	            	 istwo=0;
	    	            	            	 length+=1;
	    	            	            	 score+=1;
	    	            	            	 second=1;
	    	            	            	 
	    	            	            	 System.out.println("次数:"+(count-1)+"remove(0)");
	    	            	           }
	    	            	            	 else{
	    	            	        	   if(second<length||second==length){
	    	            	        		   pane.getChildren().remove(0);
	    	            	        		   
	    	            	        		   System.out.println("次数:"+(count-1)+"remove(0)");
	    	            	        	   }
	    	            	        	   if(second>length){
	    	            	        		   pane.getChildren().remove(1);
	    	            	        		   
	    	            	        		   System.out.println("次数:"+(count-1)+"remove(1)");
	    	            	        	   }
	    	            	        	   
	    	            	          }
	    	            	            
	    	              }
	    	              pane.getChildren().add(r);   
	    }
//==============================监测碰撞函数========================================
	    public void pengzhuang(){
	    	//Rectangle r=new Rectangle(x+weigth*wcount,y+heigth*hcount,30,30);
	    	int x,y;
	    	if(count>length){
	    		for(int i=0;i<length-2;i++){
	    		 Rectangle rr=(Rectangle) pane.getChildren().get(i);
	    		 x=(int)rr.getX();
				 y=(int)rr.getY();
				 if(x==wcount*30&&y==hcount*30){
					 tl.stop();
				 }
				 
				 if(second<length||second==length){
					 if(wcount==a&&hcount==b){
						 System.out.println("haha");
						 tl.play();
					 }
				 }
				 
	    		System.out.println("碰撞函数："+x+"  "+y);
	    	}
	    	}
	    	
	    }
//=================================main函数=========================================	    
	public static void main(String[] args) {
		Application.launch(args);
	}

}

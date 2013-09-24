package practice481.memory;

import java.util.Random;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.app.Activity;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends Activity
{
int[] ButtonColors;
Button[] ButtonList;
LinearLayout Board;
LinearLayout [] rows;
int flipped;
int matchCounter;
boolean delay;
  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    ButtonColors = new int[]{ 
        0xffff0000,
        0xff00cc00,
        0xffb1f100,
        0xffc10087,
        0xffffec00,
        0xff580ead,
        0xff057d9f,
        0xffff8100,
        0xffffbb00,
        0xff1924b1,
        0xffff0000,
        0xff00cc00,
        0xffb1f100,
        0xffc10087,
        0xffffec00,
        0xff580ead,
        0xff057d9f,
        0xffff8100,
        0xffffbb00,
        0xff1924b1      
    };
    
    flipped = -1;
    matchCounter = 0;
    delay = false;
    //simple randomizing algorithm  
    Random ran = new Random();
    for(int i = 19; i >= 0; i--) {
      int j = ran.nextInt(i+1);
      int temp = ButtonColors[j];
      ButtonColors[j] = ButtonColors[i];
      ButtonColors[i] = temp;
    }

    
    ButtonList = new Button[20];
    for(int i = 0; i < 20; i++) {
      final int j = i;
      ButtonList[i] = new Button(this);
      ButtonList[i].setText("  ");
      ButtonList[i].setBackgroundColor(0xff888888);

      ButtonList[i].setOnClickListener(new OnClickListener() {
        public void onClick(View ClickedButton) {
          ButtonList[j].setBackgroundColor(ButtonColors[j]);
          if(flipped < 0) flipped = j;
          else {
            if(j == flipped) return;
            delay = true;
            CountDownTimer timer = new CountDownTimer(100, 100) {
              @Override
              public void onFinish() {
                if(ButtonColors[flipped] == ButtonColors[j]) {
                  ButtonList[j].setClickable(false);
                  ButtonList[flipped].setClickable(false);
                  ButtonList[j].setBackgroundColor(0xffeeeeee);
                  ButtonList[flipped].setBackgroundColor(0xffeeeeee);
                  flipped = -1;
                  matchCounter++;
                  if(matchCounter >= 10) {
                    //Won the game
                  }
                }
                else {
                  ButtonList[j].setBackgroundColor(0xff888888);
                  ButtonList[flipped].setBackgroundColor(0xff888888);
                  flipped = -1;
                }
                delay = false;
                  }

              @Override
              public void onTick(long millisUntilFinished)
              {
                // TODO Auto-generated method stub
                
              }
          }.start();


          }
        }
      });
    }
        
    
    Board = new LinearLayout(this);
    Board.setOrientation(LinearLayout.VERTICAL);
    Board.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
    Board.setWeightSum(10f);
    
    LinearLayout.LayoutParams butLay = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 2f);
    butLay.setMargins(20, 0, 20, 0);
    LinearLayout.LayoutParams rowLay = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 2f);
    rowLay.setMargins(0, 20, 0, 20);
    rows = new LinearLayout[5];
    for(int i = 0; i < 5; i++) {
      rows[i] = new LinearLayout(this);
      rows[i].setOrientation(LinearLayout.HORIZONTAL);
      rows[i].setWeightSum(8f);
      for(int j = i*4; j < (i+1)*4; j++) {
        rows[i].addView(ButtonList[j], butLay);
      }
      Board.addView(rows[i], rowLay);
    }

    
    
    
    RelativeLayout mainWindow = new RelativeLayout(this);
    mainWindow.addView(Board);
    setContentView(mainWindow);
  }



}

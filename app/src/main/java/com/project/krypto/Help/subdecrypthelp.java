package com.project.krypto.Help;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.project.krypto.R;

/**
 * Created by yanna on 7/30/2017.
 */

public class subdecrypthelp extends AppCompatActivity {
    int i = 1;
    int k = 1;
    private String colorCodeStart = "<font color='#EE0000'>";
    private String colorCodeEnd = "</font>";
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subhelpdecrypt);

        toolbar = (Toolbar) findViewById(R.id.toolbarsubhelpdecrypt);
        toolbar.setTitle("Decrypt");
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setBackgroundColor(getResources().getColor(R.color.Black));
        toolbar.showOverflowMenu();
        setSupportActionBar(toolbar);
        setStatusBar();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //    Toast.makeText(getApplicationContext(), "Back Pressed", Toast.LENGTH_LONG).show();
                onBackPressed();
            }
        });

        final TextView viewcipher = (TextView) findViewById(R.id.editText);
        final Button next = (Button) findViewById(R.id.nextbtn);
        final Button back = (Button) findViewById(R.id.previousbtn);
        final Button exit = (Button) findViewById(R.id.exitbtn);
        final TextView viewkey = (TextView) findViewById(R.id.editText4);
        final TextView finalcipher = (TextView) findViewById(R.id.editText3);
        final TextView shortedkey = (TextView) findViewById(R.id.editText5);
        final TextView finalkey = (TextView) findViewById(R.id.editText7);
        final TextView decrypted = (TextView) findViewById(R.id.editText8);
        final TextView popup = (TextView) findViewById(R.id.textView8);
        final TextView KA = (TextView) findViewById(R.id.KA);
        final TextView KB = (TextView) findViewById(R.id.KB);
        final TextView KC = (TextView) findViewById(R.id.KC);
        final TextView KD = (TextView) findViewById(R.id.KD);
        final TextView KE = (TextView) findViewById(R.id.KE);
        final TextView KF = (TextView) findViewById(R.id.KF);
        final TextView KG = (TextView) findViewById(R.id.KG);
        final TextView KH = (TextView) findViewById(R.id.KH);
        final TextView KI = (TextView) findViewById(R.id.KI);
        final TextView KJ = (TextView) findViewById(R.id.KJ);
        final TextView KK = (TextView) findViewById(R.id.KK);
        final TextView KL = (TextView) findViewById(R.id.KL);
        final TextView KM = (TextView) findViewById(R.id.KM);
        final TextView KN = (TextView) findViewById(R.id.KN);
        final TextView KO = (TextView) findViewById(R.id.KO);
        final TextView KP = (TextView) findViewById(R.id.KP);
        final TextView KQ = (TextView) findViewById(R.id.KQ);
        final TextView KR = (TextView) findViewById(R.id.KR);
        final TextView KS = (TextView) findViewById(R.id.KS);
        final TextView KT = (TextView) findViewById(R.id.KT);
        final TextView KU = (TextView) findViewById(R.id.KU);
        final TextView KV = (TextView) findViewById(R.id.KV);
        final TextView KW = (TextView) findViewById(R.id.KW);
        final TextView KX = (TextView) findViewById(R.id.KX);
        final TextView KY = (TextView) findViewById(R.id.KY);
        final TextView KZ = (TextView) findViewById(R.id.KZ);
        final TextView CA = (TextView) findViewById(R.id.CA);
        final TextView CB = (TextView) findViewById(R.id.CB);
        final TextView CC = (TextView) findViewById(R.id.CC);
        final TextView CD = (TextView) findViewById(R.id.CD);
        final TextView CE = (TextView) findViewById(R.id.CE);
        final TextView CF = (TextView) findViewById(R.id.CF);
        final TextView CG = (TextView) findViewById(R.id.CG);
        final TextView CH = (TextView) findViewById(R.id.CH);
        final TextView CI = (TextView) findViewById(R.id.CI);
        final TextView CJ = (TextView) findViewById(R.id.CJ);
        final TextView CK = (TextView) findViewById(R.id.CK);
        final TextView CL = (TextView) findViewById(R.id.CL);
        final TextView CM = (TextView) findViewById(R.id.CM);
        final TextView CN = (TextView) findViewById(R.id.CN);
        final TextView CO = (TextView) findViewById(R.id.CO);
        final TextView CP = (TextView) findViewById(R.id.CP);
        final TextView CQ = (TextView) findViewById(R.id.CQ);
        final TextView CR = (TextView) findViewById(R.id.CR);
        final TextView CS = (TextView) findViewById(R.id.CS);
        final TextView CT = (TextView) findViewById(R.id.CT);
        final TextView CU = (TextView) findViewById(R.id.CU);
        final TextView CV = (TextView) findViewById(R.id.CV);
        final TextView CW = (TextView) findViewById(R.id.CW);
        final TextView CX = (TextView) findViewById(R.id.CX);
        final TextView CY = (TextView) findViewById(R.id.CY);
        final TextView CZ = (TextView) findViewById(R.id.CZ);

        Intent intent = getIntent();
        String cipher = intent.getExtras().getString("CIPHER");
        String key = intent.getExtras().getString("KEY");
        //String shortkey = intent.getExtras().getString("shortkey");
        String keystring = intent.getExtras().getString("KEYSTRING");

        String temporary_key_c1 = key.toUpperCase();
        String shortkey = "";
        int count=0,length_len=0;
        do
        {
            try
            {
                char character_name[]=temporary_key_c1.toCharArray();
                length_len=character_name.length;
                count=0;
                for(int j=0;j<length_len;j++)
                {
                    if((character_name[0]==character_name[j])&&((character_name[0]>=65&&character_name[0]<=91)||(character_name[0]>=97&&character_name[0]<=123)))
                        count++;
                }

                if(count!=0)
                    temporary_key_c1=temporary_key_c1.replace(""+character_name[0],"");
                shortkey += character_name[0];

            }
            catch(Exception ex){}
        }
        while(length_len!=0);

        String[] arr = cipher.split("\\s+");
        int N = arr.length;
        String newcipher = "";
        for (int i = 0; i < N; i++) {
            newcipher = newcipher + " " + arr[i];
        }
        viewcipher.setText(newcipher);
        decrypted.setText(newcipher);
        viewkey.setText(key);
        shortedkey.setText(shortkey);
        finalkey.setText(keystring);
        String[] keystringarray = keystring.split("(?!^)");
        String stringkey1 = keystringarray[0];
        String stringkey2 = keystringarray[1];
        String stringkey3 = keystringarray[2];
        String stringkey4 = keystringarray[3];
        String stringkey5 = keystringarray[4];
        String stringkey6 = keystringarray[5];
        String stringkey7 = keystringarray[6];
        String stringkey8 = keystringarray[7];
        String stringkey9 = keystringarray[8];
        String stringkey10 = keystringarray[9];
        String stringkey11 = keystringarray[10];
        String stringkey12 = keystringarray[11];
        String stringkey13 = keystringarray[12];
        String stringkey14 = keystringarray[13];
        String stringkey15 = keystringarray[14];
        String stringkey16 = keystringarray[15];
        String stringkey17 = keystringarray[16];
        String stringkey18 = keystringarray[17];
        String stringkey19 = keystringarray[18];
        String stringkey20 = keystringarray[19];
        String stringkey21 = keystringarray[20];
        String stringkey22 = keystringarray[21];
        String stringkey23 = keystringarray[22];
        String stringkey24 = keystringarray[23];
        String stringkey25 = keystringarray[24];
        String stringkey26 = keystringarray[25];
        KA.setText(stringkey1);
        KB.setText(stringkey2);
        KC.setText(stringkey3);
        KD.setText(stringkey4);
        KE.setText(stringkey5);
        KF.setText(stringkey6);
        KG.setText(stringkey7);
        KH.setText(stringkey8);
        KI.setText(stringkey9);
        KJ.setText(stringkey10);
        KK.setText(stringkey11);
        KL.setText(stringkey12);
        KM.setText(stringkey13);
        KN.setText(stringkey14);
        KO.setText(stringkey15);
        KP.setText(stringkey16);
        KQ.setText(stringkey17);
        KR.setText(stringkey18);
        KS.setText(stringkey19);
        KT.setText(stringkey20);
        KU.setText(stringkey21);
        KV.setText(stringkey22);
        KW.setText(stringkey23);
        KX.setText(stringkey24);
        KY.setText(stringkey25);
        KZ.setText(stringkey26);

        String ch1 = keystring.toUpperCase();

        final char[] ch = ch1.toCharArray();
        String p1 = "abcdefghijklmnopqrstuvwxyz";
        char[] p3 = p1.toCharArray();
        final String s = newcipher.toUpperCase();
        final char so[] = s.toCharArray();
        char p2[] = new char[(s.length())];
        for (int i = 0; i < s.length(); i++) {
            int b = (int) so[i];
            if (b < 65 || b > 90) {
                p2[i] = s.charAt(i);
            } else
                for (int j = 0; j < 26; j++) {
                    if (ch[j] == s.charAt(i)) {
                        p2[i] = p3[j];
                        break;
                    }
                }
        }
        String clear = "";
        for (int i = 0; i < s.length(); i++) {
            clear = clear + p2[i];
        }
        final String s1 = s;
        final String clear4 = clear.toUpperCase();
        final char[] clear1 = clear4.toCharArray();
        decrypted.setText(clear4);
        finalcipher.setText(s);
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                i = k;
                k++;

                final Handler handler = new Handler();
                if (i < s.length()) {
                    int a = i + 1;
                    String yourNewString;
                    String clear2 = clear4.substring(0, i) + "<(>" + clear1[i] + "<)>" + clear4.substring(a);
                    yourNewString = clear2.replace("<(>", colorCodeStart);
                    yourNewString = yourNewString.replace("<)>", colorCodeEnd);
                    Log.d("CheckNew", yourNewString);
                    decrypted.setText(Html.fromHtml(yourNewString));
                    String youroldString;
                    String s = s1.substring(0, i) + "<(>" + so[i] + "<)>" + s1.substring(a);
                    youroldString = s.replace("<(>", colorCodeStart);
                    youroldString = youroldString.replace("<)>", colorCodeEnd);
                    Log.d("CheckNew", youroldString);
                    finalcipher.setText(Html.fromHtml(youroldString));
                    int j = i;
                    if (so[i] == ch[0]) {
                        KA.setBackgroundColor(Color.RED);
                        CA.setBackgroundColor(Color.RED);
                        popup.setVisibility(View.VISIBLE);
                        popup.setText(so[j] + " is substituted back to A");
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                KA.setBackgroundResource(R.drawable.tablecell);
                                CA.setBackgroundResource(R.drawable.tablecell);
                                popup.setVisibility(View.INVISIBLE);
                            }
                        }, 1000);
                    } else if (so[i] == ch[1]) {
                        KB.setBackgroundColor(Color.RED);
                        CB.setBackgroundColor(Color.RED);
                        popup.setText(so[j] + " is substituted back to B");
                        popup.setVisibility(View.VISIBLE);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                KB.setBackgroundResource(R.drawable.tablecell);
                                CB.setBackgroundResource(R.drawable.tablecell);
                                popup.setVisibility(View.INVISIBLE);
                            }
                        }, 1000);
                    } else if (so[i] == ch[2]) {
                        KC.setBackgroundColor(Color.RED);
                        CC.setBackgroundColor(Color.RED);
                        popup.setText(so[j] + " is substituted back to C");
                        popup.setVisibility(View.VISIBLE);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                KC.setBackgroundResource(R.drawable.tablecell);
                                CC.setBackgroundResource(R.drawable.tablecell);
                                popup.setVisibility(View.INVISIBLE);
                            }
                        }, 1000);
                    } else if (so[i] == ch[3]) {
                        KD.setBackgroundColor(Color.RED);
                        CD.setBackgroundColor(Color.RED);
                        popup.setText(so[j] + " is substituted back to D");
                        popup.setVisibility(View.VISIBLE);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                KD.setBackgroundResource(R.drawable.tablecell);
                                CD.setBackgroundResource(R.drawable.tablecell);
                                popup.setVisibility(View.INVISIBLE);
                            }
                        }, 1000);
                    } else if (so[i] == ch[4]) {
                        KE.setBackgroundColor(Color.RED);
                        CE.setBackgroundColor(Color.RED);
                        popup.setText(so[j] + " is substituted back to E");
                        popup.setVisibility(View.VISIBLE);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                KE.setBackgroundResource(R.drawable.tablecell);
                                CE.setBackgroundResource(R.drawable.tablecell);
                                popup.setVisibility(View.INVISIBLE);
                            }
                        }, 1000);
                    } else if (so[i] == ch[5]) {
                        KF.setBackgroundColor(Color.RED);
                        CF.setBackgroundColor(Color.RED);
                        popup.setText(so[j] + " is substituted back to F");
                        popup.setVisibility(View.VISIBLE);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                KF.setBackgroundResource(R.drawable.tablecell);
                                CF.setBackgroundResource(R.drawable.tablecell);
                                popup.setVisibility(View.INVISIBLE);
                            }
                        }, 1000);
                    } else if (so[i] == ch[6]) {
                        KG.setBackgroundColor(Color.RED);
                        CG.setBackgroundColor(Color.RED);
                        popup.setText(so[j] + " is substituted back to G");
                        popup.setVisibility(View.VISIBLE);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                KG.setBackgroundResource(R.drawable.tablecell);
                                CG.setBackgroundResource(R.drawable.tablecell);
                                popup.setVisibility(View.INVISIBLE);
                            }
                        }, 1000);
                    } else if (so[i] == ch[7]) {
                        KH.setBackgroundColor(Color.RED);
                        CH.setBackgroundColor(Color.RED);
                        popup.setText(so[j] + " is substituted back to H");
                        popup.setVisibility(View.VISIBLE);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                KH.setBackgroundResource(R.drawable.tablecell);
                                CH.setBackgroundResource(R.drawable.tablecell);
                                popup.setVisibility(View.INVISIBLE);
                            }
                        }, 1000);

                    } else if (so[i] == ch[8]) {
                        KI.setBackgroundColor(Color.RED);
                        CI.setBackgroundColor(Color.RED);
                        popup.setText(so[j] + " is substituted back to I");
                        popup.setVisibility(View.VISIBLE);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                KI.setBackgroundResource(R.drawable.tablecell);
                                CI.setBackgroundResource(R.drawable.tablecell);
                                popup.setVisibility(View.INVISIBLE);
                            }
                        }, 1000);
                    } else if (so[i] == ch[9]) {
                        KJ.setBackgroundColor(Color.RED);
                        CJ.setBackgroundColor(Color.RED);
                        popup.setText(so[j] + " is substituted back to J");
                        popup.setVisibility(View.VISIBLE);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                KJ.setBackgroundResource(R.drawable.tablecell);
                                CJ.setBackgroundResource(R.drawable.tablecell);
                                popup.setVisibility(View.INVISIBLE);
                            }
                        }, 1000);
                    } else if (so[i] == ch[10]) {
                        KK.setBackgroundColor(Color.RED);
                        CK.setBackgroundColor(Color.RED);
                        popup.setText(so[j] + " is substituted back to K");
                        popup.setVisibility(View.VISIBLE);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                KK.setBackgroundResource(R.drawable.tablecell);
                                CK.setBackgroundResource(R.drawable.tablecell);
                                popup.setVisibility(View.INVISIBLE);
                            }
                        }, 1000);
                    } else if (so[i] == ch[11]) {
                        KL.setBackgroundColor(Color.RED);
                        CL.setBackgroundColor(Color.RED);
                        popup.setText(so[j] + " is substituted back to L");
                        popup.setVisibility(View.VISIBLE);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                KL.setBackgroundResource(R.drawable.tablecell);
                                CL.setBackgroundResource(R.drawable.tablecell);
                                popup.setVisibility(View.INVISIBLE);
                            }
                        }, 1000);
                    } else if (so[i] == ch[12]) {
                        KM.setBackgroundColor(Color.RED);
                        CM.setBackgroundColor(Color.RED);
                        popup.setText(so[j] + " is substituted back to M");
                        popup.setVisibility(View.VISIBLE);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                KM.setBackgroundResource(R.drawable.tablecell);
                                CM.setBackgroundResource(R.drawable.tablecell);
                                popup.setVisibility(View.INVISIBLE);
                            }
                        }, 1000);
                    } else if (so[i] == ch[13]) {
                        KN.setBackgroundColor(Color.RED);
                        CN.setBackgroundColor(Color.RED);
                        popup.setText(so[j] + " is substituted back to N");
                        popup.setVisibility(View.VISIBLE);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                KN.setBackgroundResource(R.drawable.tablecell);
                                CN.setBackgroundResource(R.drawable.tablecell);
                                popup.setVisibility(View.INVISIBLE);
                            }
                        }, 1000);
                    } else if (so[i] == ch[14]) {
                        KO.setBackgroundColor(Color.RED);
                        CO.setBackgroundColor(Color.RED);
                        popup.setText(so[j] + " is substituted back to O");
                        popup.setVisibility(View.VISIBLE);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                KO.setBackgroundResource(R.drawable.tablecell);
                                CO.setBackgroundResource(R.drawable.tablecell);
                                popup.setVisibility(View.INVISIBLE);
                            }
                        }, 1000);
                    } else if (so[i] == ch[15]) {
                        KP.setBackgroundColor(Color.RED);
                        CP.setBackgroundColor(Color.RED);
                        popup.setText(so[j] + " is substituted back to P");
                        popup.setVisibility(View.VISIBLE);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                KP.setBackgroundResource(R.drawable.tablecell);
                                CP.setBackgroundResource(R.drawable.tablecell);
                                popup.setVisibility(View.INVISIBLE);
                            }
                        }, 1000);
                    } else if (so[i] == ch[16]) {
                        KQ.setBackgroundColor(Color.RED);
                        CQ.setBackgroundColor(Color.RED);
                        popup.setText(so[j] + " is substituted back to Q");
                        popup.setVisibility(View.VISIBLE);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                KQ.setBackgroundResource(R.drawable.tablecell);
                                CQ.setBackgroundResource(R.drawable.tablecell);
                                popup.setVisibility(View.INVISIBLE);
                            }
                        }, 1000);
                    } else if (so[i] == ch[17]) {
                        KR.setBackgroundColor(Color.RED);
                        CR.setBackgroundColor(Color.RED);
                        popup.setText(so[j] + " is substituted back to R");
                        popup.setVisibility(View.VISIBLE);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                KR.setBackgroundResource(R.drawable.tablecell);
                                CR.setBackgroundResource(R.drawable.tablecell);
                                popup.setVisibility(View.INVISIBLE);
                            }
                        }, 1000);
                    } else if (so[i] == ch[18]) {
                        KS.setBackgroundColor(Color.RED);
                        CS.setBackgroundColor(Color.RED);
                        popup.setText(so[j] + " is substituted back to S");
                        popup.setVisibility(View.VISIBLE);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                KS.setBackgroundResource(R.drawable.tablecell);
                                CS.setBackgroundResource(R.drawable.tablecell);
                                popup.setVisibility(View.INVISIBLE);
                            }
                        }, 1000);
                    } else if (so[i] == ch[19]) {
                        KT.setBackgroundColor(Color.RED);
                        CT.setBackgroundColor(Color.RED);
                        popup.setText(so[j] + " is substituted back to T");
                        popup.setVisibility(View.VISIBLE);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                KT.setBackgroundResource(R.drawable.tablecell);
                                CT.setBackgroundResource(R.drawable.tablecell);
                                popup.setVisibility(View.INVISIBLE);
                            }
                        }, 1000);
                    } else if (so[i] == ch[20]) {
                        KU.setBackgroundColor(Color.RED);
                        CU.setBackgroundColor(Color.RED);
                        popup.setText(so[j] + " is substituted back to U");
                        popup.setVisibility(View.VISIBLE);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                KU.setBackgroundResource(R.drawable.tablecell);
                                CU.setBackgroundResource(R.drawable.tablecell);
                                popup.setVisibility(View.INVISIBLE);
                            }
                        }, 1000);
                    } else if (so[i] == ch[21]) {
                        KV.setBackgroundColor(Color.RED);
                        CV.setBackgroundColor(Color.RED);
                        popup.setText(so[j] + " is substituted back to V");
                        popup.setVisibility(View.VISIBLE);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                KV.setBackgroundResource(R.drawable.tablecell);
                                CV.setBackgroundResource(R.drawable.tablecell);
                                popup.setVisibility(View.INVISIBLE);
                            }
                        }, 1000);
                    } else if (so[i] == ch[22]) {
                        KW.setBackgroundColor(Color.RED);
                        CW.setBackgroundColor(Color.RED);
                        popup.setText(so[j] + " is substituted back to W");
                        popup.setVisibility(View.VISIBLE);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                KW.setBackgroundResource(R.drawable.tablecell);
                                CW.setBackgroundResource(R.drawable.tablecell);
                                popup.setVisibility(View.INVISIBLE);
                            }
                        }, 1000);
                    } else if (so[i] == ch[23]) {
                        KX.setBackgroundColor(Color.RED);
                        CX.setBackgroundColor(Color.RED);
                        popup.setText(so[j] + " is substituted back to X");
                        popup.setVisibility(View.VISIBLE);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                KX.setBackgroundResource(R.drawable.tablecell);
                                CX.setBackgroundResource(R.drawable.tablecell);
                                popup.setVisibility(View.INVISIBLE);
                            }
                        }, 1000);
                    } else if (so[i] == ch[24]) {
                        KY.setBackgroundColor(Color.RED);
                        CY.setBackgroundColor(Color.RED);
                        popup.setText(so[j] + " is substituted back to Y");
                        popup.setVisibility(View.VISIBLE);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                KY.setBackgroundResource(R.drawable.tablecell);
                                CY.setBackgroundResource(R.drawable.tablecell);
                                popup.setVisibility(View.INVISIBLE);
                            }
                        }, 1000);
                    } else if (so[i] == ch[25]) {
                        KZ.setBackgroundColor(Color.RED);
                        CZ.setBackgroundColor(Color.RED);
                        popup.setText(so[j] + " is substituted back to Z");
                        popup.setVisibility(View.VISIBLE);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                KZ.setBackgroundResource(R.drawable.tablecell);
                                CZ.setBackgroundResource(R.drawable.tablecell);
                                popup.setVisibility(View.INVISIBLE);
                            }
                        }, 1000);
                    } else {
                        popup.setText("Non-English Character are not effected (e.g Space)");
                        popup.setVisibility(View.VISIBLE);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                popup.setVisibility(View.INVISIBLE);
                            }
                        }, 1000);
                    }
                } else if (i == s.length()) {
                    i = 1;
                    k = 1;
                    decrypted.setText(clear4);
                    finalcipher.setText(s1);
                    AlertDialog.Builder builder = new AlertDialog.Builder(subdecrypthelp.this);
                    builder.setCancelable(true);
                    builder.setTitle("Done!");
                    builder.setMessage("Decryption is Done!");
                    builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
                    builder.show();
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                i = k - 2;
                k--;

                final Handler handler = new Handler();
                if (i < s.length() && i > 0) {
                    int a = i + 1;
                    String yourNewString;
                    String clear2 = clear4.substring(0, i) + "<(>" + clear1[i] + "<)>" + clear4.substring(a);
                    yourNewString = clear2.replace("<(>", colorCodeStart);
                    yourNewString = yourNewString.replace("<)>", colorCodeEnd);
                    Log.d("CheckNew", yourNewString);
                    decrypted.setText(Html.fromHtml(yourNewString));
                    String youroldString;
                    String s = s1.substring(0, i) + "<(>" + so[i] + "<)>" + s1.substring(a);
                    youroldString = s.replace("<(>", colorCodeStart);
                    youroldString = youroldString.replace("<)>", colorCodeEnd);
                    Log.d("CheckNew", youroldString);
                    finalcipher.setText(Html.fromHtml(youroldString));
                    int j = i;
                    if (so[i] == ch[0]) {
                        KA.setBackgroundColor(Color.RED);
                        CA.setBackgroundColor(Color.RED);
                        popup.setVisibility(View.VISIBLE);
                        popup.setText(so[j] + " is substituted back to A");
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                KA.setBackgroundResource(R.drawable.tablecell);
                                CA.setBackgroundResource(R.drawable.tablecell);
                                popup.setVisibility(View.INVISIBLE);
                            }
                        }, 1000);
                    } else if (so[i] == ch[1]) {
                        KB.setBackgroundColor(Color.RED);
                        CB.setBackgroundColor(Color.RED);
                        popup.setText(so[j] + " is substituted back to B");
                        popup.setVisibility(View.VISIBLE);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                KB.setBackgroundResource(R.drawable.tablecell);
                                CB.setBackgroundResource(R.drawable.tablecell);
                                popup.setVisibility(View.INVISIBLE);
                            }
                        }, 1000);
                    } else if (so[i] == ch[2]) {
                        KC.setBackgroundColor(Color.RED);
                        CC.setBackgroundColor(Color.RED);
                        popup.setText(so[j] + " is substituted back to C");
                        popup.setVisibility(View.VISIBLE);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                KC.setBackgroundResource(R.drawable.tablecell);
                                CC.setBackgroundResource(R.drawable.tablecell);
                                popup.setVisibility(View.INVISIBLE);
                            }
                        }, 1000);
                    } else if (so[i] == ch[3]) {
                        KD.setBackgroundColor(Color.RED);
                        CD.setBackgroundColor(Color.RED);
                        popup.setText(so[j] + " is substituted back to D");
                        popup.setVisibility(View.VISIBLE);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                KD.setBackgroundResource(R.drawable.tablecell);
                                CD.setBackgroundResource(R.drawable.tablecell);
                                popup.setVisibility(View.INVISIBLE);
                            }
                        }, 1000);
                    } else if (so[i] == ch[4]) {
                        KE.setBackgroundColor(Color.RED);
                        CE.setBackgroundColor(Color.RED);
                        popup.setText(so[j] + " is substituted back to E");
                        popup.setVisibility(View.VISIBLE);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                KE.setBackgroundResource(R.drawable.tablecell);
                                CE.setBackgroundResource(R.drawable.tablecell);
                                popup.setVisibility(View.INVISIBLE);
                            }
                        }, 1000);
                    } else if (so[i] == ch[5]) {
                        KF.setBackgroundColor(Color.RED);
                        CF.setBackgroundColor(Color.RED);
                        popup.setText(so[j] + " is substituted back to F");
                        popup.setVisibility(View.VISIBLE);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                KF.setBackgroundResource(R.drawable.tablecell);
                                CF.setBackgroundResource(R.drawable.tablecell);
                                popup.setVisibility(View.INVISIBLE);
                            }
                        }, 1000);
                    } else if (so[i] == ch[6]) {
                        KG.setBackgroundColor(Color.RED);
                        CG.setBackgroundColor(Color.RED);
                        popup.setText(so[j] + " is substituted back to G");
                        popup.setVisibility(View.VISIBLE);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                KG.setBackgroundResource(R.drawable.tablecell);
                                CG.setBackgroundResource(R.drawable.tablecell);
                                popup.setVisibility(View.INVISIBLE);
                            }
                        }, 1000);
                    } else if (so[i] == ch[7]) {
                        KH.setBackgroundColor(Color.RED);
                        CH.setBackgroundColor(Color.RED);
                        popup.setText(so[j] + " is substituted back to H");
                        popup.setVisibility(View.VISIBLE);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                KH.setBackgroundResource(R.drawable.tablecell);
                                CH.setBackgroundResource(R.drawable.tablecell);
                                popup.setVisibility(View.INVISIBLE);
                            }
                        }, 1000);

                    } else if (so[i] == ch[8]) {
                        KI.setBackgroundColor(Color.RED);
                        CI.setBackgroundColor(Color.RED);
                        popup.setText(so[j] + " is substituted back to I");
                        popup.setVisibility(View.VISIBLE);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                KI.setBackgroundResource(R.drawable.tablecell);
                                CI.setBackgroundResource(R.drawable.tablecell);
                                popup.setVisibility(View.INVISIBLE);
                            }
                        }, 1000);
                    } else if (so[i] == ch[9]) {
                        KJ.setBackgroundColor(Color.RED);
                        CJ.setBackgroundColor(Color.RED);
                        popup.setText(so[j] + " is substituted back to J");
                        popup.setVisibility(View.VISIBLE);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                KJ.setBackgroundResource(R.drawable.tablecell);
                                CJ.setBackgroundResource(R.drawable.tablecell);
                                popup.setVisibility(View.INVISIBLE);
                            }
                        }, 1000);
                    } else if (so[i] == ch[10]) {
                        KK.setBackgroundColor(Color.RED);
                        CK.setBackgroundColor(Color.RED);
                        popup.setText(so[j] + " is substituted back to K");
                        popup.setVisibility(View.VISIBLE);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                KK.setBackgroundResource(R.drawable.tablecell);
                                CK.setBackgroundResource(R.drawable.tablecell);
                                popup.setVisibility(View.INVISIBLE);
                            }
                        }, 1000);
                    } else if (so[i] == ch[11]) {
                        KL.setBackgroundColor(Color.RED);
                        CL.setBackgroundColor(Color.RED);
                        popup.setText(so[j] + " is substituted back to L");
                        popup.setVisibility(View.VISIBLE);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                KL.setBackgroundResource(R.drawable.tablecell);
                                CL.setBackgroundResource(R.drawable.tablecell);
                                popup.setVisibility(View.INVISIBLE);
                            }
                        }, 1000);
                    } else if (so[i] == ch[12]) {
                        KM.setBackgroundColor(Color.RED);
                        CM.setBackgroundColor(Color.RED);
                        popup.setText(so[j] + " is substituted back to M");
                        popup.setVisibility(View.VISIBLE);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                KM.setBackgroundResource(R.drawable.tablecell);
                                CM.setBackgroundResource(R.drawable.tablecell);
                                popup.setVisibility(View.INVISIBLE);
                            }
                        }, 1000);
                    } else if (so[i] == ch[13]) {
                        KN.setBackgroundColor(Color.RED);
                        CN.setBackgroundColor(Color.RED);
                        popup.setText(so[j] + " is substituted back to N");
                        popup.setVisibility(View.VISIBLE);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                KN.setBackgroundResource(R.drawable.tablecell);
                                CN.setBackgroundResource(R.drawable.tablecell);
                                popup.setVisibility(View.INVISIBLE);
                            }
                        }, 1000);
                    } else if (so[i] == ch[14]) {
                        KO.setBackgroundColor(Color.RED);
                        CO.setBackgroundColor(Color.RED);
                        popup.setText(so[j] + " is substituted back to O");
                        popup.setVisibility(View.VISIBLE);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                KO.setBackgroundResource(R.drawable.tablecell);
                                CO.setBackgroundResource(R.drawable.tablecell);
                                popup.setVisibility(View.INVISIBLE);
                            }
                        }, 1000);
                    } else if (so[i] == ch[15]) {
                        KP.setBackgroundColor(Color.RED);
                        CP.setBackgroundColor(Color.RED);
                        popup.setText(so[j] + " is substituted back to P");
                        popup.setVisibility(View.VISIBLE);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                KP.setBackgroundResource(R.drawable.tablecell);
                                CP.setBackgroundResource(R.drawable.tablecell);
                                popup.setVisibility(View.INVISIBLE);
                            }
                        }, 1000);
                    } else if (so[i] == ch[16]) {
                        KQ.setBackgroundColor(Color.RED);
                        CQ.setBackgroundColor(Color.RED);
                        popup.setText(so[j] + " is substituted back to Q");
                        popup.setVisibility(View.VISIBLE);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                KQ.setBackgroundResource(R.drawable.tablecell);
                                CQ.setBackgroundResource(R.drawable.tablecell);
                                popup.setVisibility(View.INVISIBLE);
                            }
                        }, 1000);
                    } else if (so[i] == ch[17]) {
                        KR.setBackgroundColor(Color.RED);
                        CR.setBackgroundColor(Color.RED);
                        popup.setText(so[j] + " is substituted back to R");
                        popup.setVisibility(View.VISIBLE);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                KR.setBackgroundResource(R.drawable.tablecell);
                                CR.setBackgroundResource(R.drawable.tablecell);
                                popup.setVisibility(View.INVISIBLE);
                            }
                        }, 1000);
                    } else if (so[i] == ch[18]) {
                        KS.setBackgroundColor(Color.RED);
                        CS.setBackgroundColor(Color.RED);
                        popup.setText(so[j] + " is substituted back to S");
                        popup.setVisibility(View.VISIBLE);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                KS.setBackgroundResource(R.drawable.tablecell);
                                CS.setBackgroundResource(R.drawable.tablecell);
                                popup.setVisibility(View.INVISIBLE);
                            }
                        }, 1000);
                    } else if (so[i] == ch[19]) {
                        KT.setBackgroundColor(Color.RED);
                        CT.setBackgroundColor(Color.RED);
                        popup.setText(so[j] + " is substituted back to T");
                        popup.setVisibility(View.VISIBLE);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                KT.setBackgroundResource(R.drawable.tablecell);
                                CT.setBackgroundResource(R.drawable.tablecell);
                                popup.setVisibility(View.INVISIBLE);
                            }
                        }, 1000);
                    } else if (so[i] == ch[20]) {
                        KU.setBackgroundColor(Color.RED);
                        CU.setBackgroundColor(Color.RED);
                        popup.setText(so[j] + " is substituted back to U");
                        popup.setVisibility(View.VISIBLE);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                KU.setBackgroundResource(R.drawable.tablecell);
                                CU.setBackgroundResource(R.drawable.tablecell);
                                popup.setVisibility(View.INVISIBLE);
                            }
                        }, 1000);
                    } else if (so[i] == ch[21]) {
                        KV.setBackgroundColor(Color.RED);
                        CV.setBackgroundColor(Color.RED);
                        popup.setText(so[j] + " is substituted back to V");
                        popup.setVisibility(View.VISIBLE);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                KV.setBackgroundResource(R.drawable.tablecell);
                                CV.setBackgroundResource(R.drawable.tablecell);
                                popup.setVisibility(View.INVISIBLE);
                            }
                        }, 1000);
                    } else if (so[i] == ch[22]) {
                        KW.setBackgroundColor(Color.RED);
                        CW.setBackgroundColor(Color.RED);
                        popup.setText(so[j] + " is substituted back to W");
                        popup.setVisibility(View.VISIBLE);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                KW.setBackgroundResource(R.drawable.tablecell);
                                CW.setBackgroundResource(R.drawable.tablecell);
                                popup.setVisibility(View.INVISIBLE);
                            }
                        }, 1000);
                    } else if (so[i] == ch[23]) {
                        KX.setBackgroundColor(Color.RED);
                        CX.setBackgroundColor(Color.RED);
                        popup.setText(so[j] + " is substituted back to X");
                        popup.setVisibility(View.VISIBLE);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                KX.setBackgroundResource(R.drawable.tablecell);
                                CX.setBackgroundResource(R.drawable.tablecell);
                                popup.setVisibility(View.INVISIBLE);
                            }
                        }, 1000);
                    } else if (so[i] == ch[24]) {
                        KY.setBackgroundColor(Color.RED);
                        CY.setBackgroundColor(Color.RED);
                        popup.setText(so[j] + " is substituted back to Y");
                        popup.setVisibility(View.VISIBLE);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                KY.setBackgroundResource(R.drawable.tablecell);
                                CY.setBackgroundResource(R.drawable.tablecell);
                                popup.setVisibility(View.INVISIBLE);
                            }
                        }, 1000);
                    } else if (so[i] == ch[25]) {
                        KZ.setBackgroundColor(Color.RED);
                        CZ.setBackgroundColor(Color.RED);
                        popup.setText(so[j] + " is substituted back to Z");
                        popup.setVisibility(View.VISIBLE);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                KZ.setBackgroundResource(R.drawable.tablecell);
                                CZ.setBackgroundResource(R.drawable.tablecell);
                                popup.setVisibility(View.INVISIBLE);
                            }
                        }, 1000);
                    } else {
                        popup.setText("Non-English Character are not effected (e.g Space)");
                        popup.setVisibility(View.VISIBLE);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                popup.setVisibility(View.INVISIBLE);
                            }
                        }, 1000);
                    }
                } else if (i <= 0) {
                    i = 1;
                    k = 1;
                    decrypted.setText(clear4);
                    finalcipher.setText(s1);
                    AlertDialog.Builder builder = new AlertDialog.Builder(subdecrypthelp.this);
                    builder.setCancelable(true);
                    builder.setTitle("Done!");
                    builder.setMessage("Nothing to Decrypt!");
                    builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
                    builder.show();
                }
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                i = 1;
                k = 1;
                decrypted.setText(clear4);
                finalcipher.setText(s1);
                AlertDialog.Builder builder = new AlertDialog.Builder(subdecrypthelp.this);
                builder.setCancelable(true);
                builder.setTitle("Reset!");
                builder.setMessage("Process Restarted!");
                builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                builder.show();
            }
        });
    }

    public void setStatusBar()
    {
        Window window = getWindow();
        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        // finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.Black));
    }

}

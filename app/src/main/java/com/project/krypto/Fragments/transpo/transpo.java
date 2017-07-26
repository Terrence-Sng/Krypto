package com.project.krypto.Fragments.transpo;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.project.krypto.Game.GameActivity;
import com.project.krypto.Game.viglvl;
import com.project.krypto.R;

import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link transpo.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link transpo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class transpo extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String cipherFromGame;
    private String level;
    private boolean fromGame;
    private Button back;

    private OnFragmentInteractionListener mListener;

    public transpo() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment transpo.
     */
    // TODO: Rename and change types and number of parameters
    public static transpo newInstance(String param1, String param2) {
        transpo fragment = new transpo();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            cipherFromGame = getArguments().getString("GameCipher");
            fromGame = getArguments().getBoolean("fromGame");
            level = getArguments().getString("level");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_transpo, container, false);
        setHasOptionsMenu(true);
        back = (Button) view.findViewById(R.id.backButtonGameTranspo);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent game;
                switch (level)
                {
                    case "1" :  game = new Intent (getContext(), GameActivity.class);
                        startActivity(game);
                        break;
                    case "2" : game = new Intent (getContext(), viglvl.class);
                        startActivity(game);
                        break;
                    case "3":
                        break;
                    case "4":
                        break;
                }
            }
        });
        final EditText mEdit = (EditText) view.findViewById(R.id.editText);
        if(fromGame==true)
        {
            mEdit.setText(cipherFromGame);
            back.setVisibility(View.VISIBLE);
        }
        else{
            back.setVisibility(View.GONE);
        }
        final EditText mEdit2 = (EditText) view.findViewById(R.id.blksize);
        final TextView textView = (TextView) view.findViewById(R.id.outputText);
        Button transpose = (Button) view.findViewById(R.id.transpo);
        transpose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = mEdit.getText().toString();
                int key = Integer.valueOf(String.valueOf(mEdit2.getText()));

                message = "TIOXSTZHCWJOHYEKNUVEDQBFMELOUROPRAG";

                textView.setText("");
                TransposeByBlk(message, key, textView);
            }
        });

        Button encrypt = (Button) view.findViewById(R.id.encrypt);
        encrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = mEdit.getText().toString();
                int rows = Integer.valueOf(String.valueOf(mEdit2.getText()));

                //sample text
                message = "THEQUICKBROWNFOXJUMPSOVERTHELAZYDOG";

                int choice = 1;
                textView.setText("");
                encORdec(message, rows, textView, choice);
            }
        });

        Button decrypt = (Button) view.findViewById(R.id.decrypt);
        decrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = mEdit.getText().toString();
                int rows = Integer.valueOf(String.valueOf(mEdit2.getText()));

                //sample message
                message = "TXZWHKVQMOOAOTCOEUDFLRRISHJYNEBEUPG";

                int choice = 2;
                textView.setText("");
                encORdec(message, rows, textView, choice);
            }
        });
    /*
        Button exampleBlk = (Button) view.findViewById(R.id.ex_Blk);
        exampleBlk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("");
                textView.setText("======Example======\n" +
                        "Text: TIOXSTZHCWJOHYEKNUVEDQBFMELOUROPRAG\n" +
                        "Blk size: 7\n\n" +
                        "TIOXSTZ\n" +
                        "HCWJOHY\n" +
                        "EKNUVED\n" +
                        "QBFMELO\n" +
                        "UROPRAG\n" +
                        "\nDeciphered text by block size 7 is: THEQUICKBROWNFOXJUMPSOVERTHELAZYDOG\n");
            }
        });

        Button exampleEnc = (Button) view.findViewById(R.id.ex_Enc);
        exampleEnc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("");
                textView.setText("======Example======\n" +
                        "Text: MEETMEATPARK\n" +
                        "Rows: 2\n\n" +
                        "===Encryption===\n" +
                        "Cipher matrix:\n" +
                        "M E M A P R \n" +
                        "E T E T A K \n" +
                        "\nCipher matrix:\n" +
                        "M M P E E A \n" +
                        "E A R T T K \n" +
                        "\nCipher text:MMPEEAEARTTK");
            }
        });

        Button exampleDec = (Button) view.findViewById(R.id.ex_Dec);
        exampleDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("");
                textView.setText("======Example====== \n" +
                        "Text: MMPEEAEARTTK\n" +
                        "Rows: 2\n\n" +
                        "===Decryption===\n" +
                        "Cipher matrix\n" +
                        "M M P E E A \n" +
                        "E A R T T K \n" +
                        "\nCipher matrix\n" +
                        "M E M A P R \n" +
                        "E T E T A K \n" +
                        "\nPlain text:MEETMEATPARK");
            }
        });
*/
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void TransposeByBlk(String message, int key, TextView textView){
// Letters in the x-axis
        int x = 0;
// Letters in the y-axis
        int y = 0;

// Prompt the user
        System.out.print("Transpose text by blocks of? : ");

// Read a line of text from the user.
        //Scanner scan = new Scanner(System.in);
        //key = mEdit2;//scan.nextLine();
        int result = key;

//sample text
        //message = "TIOXSTZHCWJOHYEKNUVEDQBFMELOUROPRAG";
        //message = "THEOTRSHESREIAITSCRTUSEAOSYTISGMETUSTEPCHMTMFUUIN";


//Display the input back to the user.
        //System.out.println("Your Message is " + message);
        textView.append("Message entered: " + message + "\n");

        int msgchar = message.length();
        int keycahr = result;//key.length();

        if (!((msgchar % keycahr) == 0)) {

            do {
                message = message + "x";
                msgchar = message.length();
            } while (!((msgchar % keycahr) == 0));

        }

// To set the temp as [x][y]
        char temp[][] = new char[result][message.length()];
        char msg[] = message.toCharArray();
// To populate the array
        x = 0;
        y = 0;
// To convert the message into an array of char
        for (int i = 0; i < msg.length; i++) {
            temp[x][y] = msg[i];
            if (x == (result - 1)) {
                x = 0;
                y = y + 1;
            } // Close if
            else {
                x++;
            }
        } // Close for loop

// To sort the key
        char t[] = new char[result];
        //t = key.toCharArray();
        Arrays.sort(t);
        textView.append("\nCipher Matrix\n");
        for (int j = 0; j < y; j++) {
            for (int i = 0; i < result; i++) {
                //System.out.print(temp[i][j]);
                textView.append(String.valueOf(temp[i][j]));
            }
            //System.out.println();
            textView.append("\n");
        }

//getting the msg
        String encryptedMessage = "";
        try {
            for (int j = 0; j < result; j++) {
                for (int i = 0; i < y/*key.length()*/; i++) {
                    encryptedMessage += temp[j][i];
                }
            }
        } catch (Exception e) {
        }

        textView.append("\nDeciphered text by block size " + result + " is: " + encryptedMessage);
        //System.exit(0);
    }

    public static String enc(String ip,int m_row,int m_col, TextView textView)
    {
        char op[][]=new char[100][100];
        int len = ip.length();
        String op2="";
        int i1,i2,i;
        for(i=0,i1=0,i2=0;i<len;i++)
        {
            op[i2][i1]=ip.charAt(i);
            i2++;
            if(i2==m_row)
            {
                i2=0;
                i1++;
            }
        }

        //System.out.println("Cipher matrix:");
        textView.append("Cipher matrix:\n");
        for(i1=0;i1<m_row;i1++)
        {
            for(i2=0;i2<m_col;i2++)
            {
                //System.out.print(op[i1][i2]+" ");
                textView.append(op[i1][i2]+" ");
            }
            //System.out.println();
            textView.append("\n");
        }

        for(i1=0;i1<m_row;i1++)
        {
            for(i2=0;i2<m_col;i2++)
            {
                op2 = op2+op[i1][i2];
            }
        }
        return (op2);
    }

    public static String dec(String ip,int m_row,int m_col, TextView textView)
    {
        char op[][]=new char[100][100];
        int len = ip.length();
        String op2="";
        int i1,i2,i;
        for(i=0,i1=0,i2=0;i<len;i++)
        {
            op[i1][i2]=ip.charAt(i);
            i2++;
            if(i2==m_col)
            {
                i2=0;
                i1++;
            }
        }

        //System.out.println("Cipher matrix");
        textView.append("Cipher matrix\n");
        for(i1=0;i1<m_row;i1++)
        {
            for(i2=0;i2<m_col;i2++)
            {
                //System.out.print(op[i1][i2]+" ");
                textView.append(op[i1][i2]+" ");
            }
            //System.out.println();
            textView.append("\n");
        }
        for(i1=0;i1<m_col;i1++)
        {
            for(i2=0;i2<m_row;i2++)
            {
                op2 = op2+op[i2][i1];
            }
        }
        return (op2);
    }

    public void encORdec(String message, int rows, TextView textView, int choice){
        //BufferedReader obj = new BufferedReader(new InputStreamReader (System.in));

        /* O-P matrix specification */
        int m_col;
        int m_row;

        /* */
        String ip;
        int len;
        String op2;

        //System.out.println("Enter text:");
        ip = message;
        len = ip.length();

        /*Calculate matrix*/
        //System.out.println("Enter number of rows this text has:");
        m_row = rows;
        m_col = (int)Math.ceil((float)len/m_row);

        /* Option */
        //System.out.println("What do you want to perform:\n1.Encryption\n2.Decryption");
        int ch;
        ch = choice;//Integer.parseInt(obj.readLine()); //depends on the button pressed

        if (ch==1)
        {
            op2 = enc(ip,m_row,m_col, textView);
            op2 = enc(op2,m_row,m_col, textView);
            System.out.println("Cipher text:"+ op2);
            textView.append("\nCipher text:" + op2);
        }
        else if (ch==2)
        {
            op2 = dec(ip,m_row,m_col, textView);
            op2 = dec(op2,m_row,m_col, textView);
            System.out.println("Plain text:"+ op2);
            textView.append("\nPlain text:" + op2);
        }
        else
        {
            System.out.println("Invalid Choice");
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.transpomain, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}

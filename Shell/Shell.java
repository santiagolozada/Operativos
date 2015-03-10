import javax.swing.*;
import java.awt.Color;
import java.awt.event.*;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Shell extends JFrame{
	
	//elementos gráficos
	JTextField tComando;
	JButton bEjecutar, bBorrar,bCerrar;
	JTextArea tResultado;
	JScrollPane sPane;

	//oyente de click de botón
	ActionListener alEjecutar,alBorrar,alSalir;

	public Shell(){
		setSize(700,600);
		setTitle(System.getProperty("os.name"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void graficos(){
		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.YELLOW);
		//cuadro de texto
		tComando = new JTextField();
		tComando.setBounds(50,50,250,30);
		add(tComando);
		//botón para ejecutar comando
		bEjecutar = new JButton("Ejecutar");
		bEjecutar.setBounds(350,50,150,30);
		add(bEjecutar);
		bEjecutar.addActionListener(alEjecutar);

		//botón para para borrar comandos 
		bBorrar=new JButton("Borrar");
		bBorrar.setBounds(550,150,130,30);
		add(bBorrar);
		bBorrar.addActionListener(alBorrar);

		//botón para cerrar aplicación
		bCerrar=new JButton("Salir");
		bCerrar.setBounds(550,200,130,30);
		add(bCerrar);
		bCerrar.addActionListener(alSalir);
		//área de texto
		tResultado = new JTextArea();
		tResultado.setBounds(50,130,600,370);
		tResultado.setBackground(Color.BLACK);
		tResultado.setForeground(Color.GREEN);
		tResultado.setEditable(false);
		//scroll pane
		sPane = new JScrollPane(tResultado);
		sPane.setBounds(50,120,500,400);
		add(sPane);
		//
		setVisible(true);
	}

	private void acciones(){
		alEjecutar = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				ejecutar();
			}
		};

		alBorrar= new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Borrar();
			}
		};

		alSalir= new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(EXIT_ON_CLOSE);
			}
		};
	}

	private void ejecutar(){

		Process proc; 
		InputStream is_in;
		String s_aux;
		BufferedReader br;

		try
		{

			String os=System.getProperty("os.name");
			String comando=null;

			if (os.equals("Linux")){
				comando="";
			}else{
				comando="cmd /c";

			}
			comando=comando+" "+tComando.getText();

			proc = Runtime.getRuntime().exec(comando);
			is_in=proc.getInputStream();
			br=new BufferedReader (new InputStreamReader (is_in));
			s_aux = br.readLine();
            while (s_aux!=null)
            {
            	tResultado.setText(tResultado.getText()+s_aux+"\n");
                s_aux = br.readLine();
            } 
		}
		catch(Exception e)
		{
			e.getMessage();
		}
		tComando.setText("");

	}

	public void Borrar(){
		tResultado.setText("");
	}

	public static void main(String args[]){
		Shell ventana = new Shell();
		ventana.acciones();	
		ventana.graficos();	
	}

}
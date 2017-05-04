/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
/**
 *
 * @author ZuraH
 */
public class FileHandler {
    
    public static void loadFile(CellGraph3D cg3d)
    {   
        try
        {
            JButton open = new JButton();
            JFileChooser fc = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Game of Life", "rle3d");
            fc.setFileFilter(filter);
            
            if(fc.showOpenDialog(open)== JFileChooser.APPROVE_OPTION)
            {
                String fullPath = fc.getSelectedFile().getAbsolutePath();
                FileReader fr = new FileReader(fullPath);
                BufferedReader br = new BufferedReader(fr);
                String start = br.readLine();//for å fjerne headeren

                //Her vil programmet load-e filen vi har lagret, for at den skal kunne gjøre dette må programmet dekryte filen
                //(filen vil bli dekrypta fra rle fil til string fil og deretteter fjerne øverste linja og fra der vil den 
                //ta for seg en og en linje. Dersom det inneholder tilstander som orignialen ikke har endres tilstandene. 
                for(int i=0; i< cg3d.getCGR().getW();i++)
                {
                    String currentLine = RunLengthDecoded(br.readLine());
                    for(int n=0; n<currentLine.length()-1;n++)
                    {
                        if(currentLine.charAt(n)=='b'&&cg3d.getCGR().table.get(i).get(n).isAlive())
                        {
                            cg3d.getCGR().table.get(i).get(n).changeState();
                        }
                        else if(currentLine.charAt(n)=='o'&&!cg3d.getCGR().table.get(i).get(n).isAlive())
                        {
                            cg3d.getCGR().table.get(i).get(n).changeState();
                        }
                    }
                }
                for(int i=0; i< cg3d.getCGT().getW();i++)
                {
                    String currentLine = RunLengthDecoded(br.readLine());
                    for(int n=0; n<currentLine.length()-1;n++)
                    {
                        if(currentLine.charAt(n)=='b'&&cg3d.getCGT().table.get(i).get(n).isAlive())
                        {
                            cg3d.getCGT().table.get(n).get(n).changeState();
                        }
                        else if(currentLine.charAt(n)=='o'&&!cg3d.getCGT().table.get(i).get(n).isAlive())
                        {
                            cg3d.getCGT().table.get(i).get(n).changeState();
                        }
                    }
                }
                for(int i=0; i< cg3d.getCGL().getW();i++)
                {
                    String currentLine = RunLengthDecoded(br.readLine());
                    for(int n=0; n<currentLine.length()-1;n++)
                    {
                        if(currentLine.charAt(n)=='b'&&cg3d.getCGL().table.get(i).get(n).isAlive())
                        {
                            cg3d.getCGL().table.get(i).get(n).changeState();
                        }
                        else if(currentLine.charAt(n)=='o'&&!cg3d.getCGL().table.get(i).get(n).isAlive())
                        {
                            cg3d.getCGL().table.get(i).get(n).changeState();
                        }
                    }
                }
                
                
                br.close();
                
            }  
        }
        catch(Exception e)
        {
            ErrorHandler.showError("Load error", "Make sure to load patterns with simmilar dimensions as the current cube");
        }
    }
    // Koden vil gå igjennom cellgraphLeft, cellgraphTop og cellgraphTop for å finne levende celler
    //dersom den finner levende celler blir disse lagret som "o", og alle celler som ikke lever vil bli lagret som "b".
    //for hver gang koden går igjennom ei rad (for finne levende celler) vil det legges til et symbol "$" i enden av raden.
    // Programmet vil også lagre dimisjonen til mønsteret først.
    public static void saveFile(CellGraph3D cg3d ) 
    {
        try
        {
         
            JButton save = new JButton();
            JFileChooser fc = new JFileChooser();
            fc.setFileFilter(new FileNameExtensionFilter("Game of Life", "rle3d"));
            
            if(fc.showSaveDialog(save)==JFileChooser.APPROVE_OPTION)
            {
                String fullPath = fc.getSelectedFile().getAbsolutePath();
                FileWriter fw = new FileWriter(fullPath+".rle3d");
                BufferedWriter bw = new BufferedWriter(fw);
                StringBuilder sb = new StringBuilder();
                sb.append("x = "+cg3d.getX()+", y = "+cg3d.getY()+", z = "+cg3d.getZ()+"\n");
                
                for(int i=0; i< cg3d.getCGR().getW();i++)
                {
                    for(int n=0; n<cg3d.getCGR().getH(); n++)
                    {
                       if(cg3d.getCGR().table.get(i).get(n).isAlive())
                       {
                           sb.append("o");
                       }
                       else
                       {
                           sb.append("b");
                       }
                    }
                    sb.append("$\n");
                } 
                
                //
                for(int i=0; i< cg3d.getCGT().getW();i++)
                {
                    for(int n=0; n<cg3d.getCGT().getH(); n++)
                    {
                       if(cg3d.getCGT().table.get(i).get(n).isAlive())
                       {
                           sb.append("o");
                       }
                       else
                       {
                           sb.append("b");
                       }
                    }
                    sb.append("$\n");
                }   
                for(int i=0; i< cg3d.getCGL().getW();i++)
                {
                    for(int n=0; n<cg3d.getCGL().getH(); n++)
                    {
                       if(cg3d.getCGL().table.get(i).get(n).isAlive())
                       {
                           sb.append("o");
                       }
                       else
                       {
                           sb.append("b");
                       }
                    }
                    sb.append("$\n");
                }    
                String s = sb.toString();
                bw.write(RunLenghtEncoded(s));
                bw.close();
                
            }
        }
        catch(Exception e)
        {
            ErrorHandler.showError("Save error", e.toString());
        }
    }
    //  Koden tar imot et string også enkoder den. Det vil si hvis den i teksten finner 
    //  gjentagende bostraver så blir det gjort om til et nummer.
    private static String RunLenghtEncoded(String s)
    {
        String encodedString ="";
        for (int i = 0, count = 1; i < s.length();i++)
        {
            if(i+1<s.length()&&s.charAt(i) == s.charAt(i+1))
            {
                count++;
            }
            else 
            {
                if(count==1)
                {
                    encodedString = encodedString.concat(Character.toString(s.charAt(i)));
                }
                else
                {
                    encodedString = encodedString.concat(Integer.toString(count)).concat(Character.toString(s.charAt(i)));
                }
                count = 1;
            }
        }     
        return encodedString;
    }
       // Her vil programmet ta imot et encoded tekst og løpe gjennom teksten, dette gjør den helt til den finner et tall(digit). 
      // Ettersom den finner et tall vil den da finne bokstaven etter tallet og lage like mange av det bokstavet som tallet. 
     // Altså, hvis programmet finner tallet 5 og bokstaven o bak tallet, vil den lagre 5 o-er.
    Koden tar imot et encoded tekst og løper gjennom teksten. Når den finner et tall så finner den det neste bosktavet
    private static String RunLengthDecoded(String s)
    {
        String decodedString ="";
        for(int i = 0; i<s.length();i++)
        {
            if(Character.isDigit(s.charAt(i)))
            {
                for(int n=0; n<(s.charAt(i)-'0');n++)
                {
                    decodedString=decodedString.concat(Character.toString(s.charAt(i+1)));
                    
                }
                i++;
            }
            else
            {
                decodedString=decodedString.concat(Character.toString(s.charAt(i)));
            }
        }
        
        return decodedString;
    }
    
}

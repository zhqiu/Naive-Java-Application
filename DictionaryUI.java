import javafx.application.Application; 
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;  
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.util.ArrayList;

public class DictionaryUI extends Application 
{
	private TextField tfInputBox = new TextField();         //�����
	private TextArea taAssociationBox = new TextArea();     //�����
	private TextArea taOutputBox = new TextArea();          //���Կ�
	
	private Button btSearch = new Button("Search");         //������ť
	
	private DictionaryData myDictionary;
	
	
	@Override   //��дApplication���е�start����
	public void start(Stage primaryStage)
	{
		//����UI
		HBox hbox = new HBox();    
		hbox.setSpacing(10);
		hbox.setAlignment(Pos.CENTER);
		hbox.getChildren().addAll(new Label("Please input:"),tfInputBox,btSearch);
		
		taAssociationBox.setEditable(false);
		taAssociationBox.setPrefColumnCount(10);
		taAssociationBox.setPrefRowCount(20);
		
		taOutputBox.setEditable(false);
		taOutputBox.setPrefColumnCount(20);
		taOutputBox.setPrefRowCount(20); 
		
		BorderPane borderPane = new BorderPane();
		borderPane.setCenter(taOutputBox);
		borderPane.setLeft(taAssociationBox);
		borderPane.setTop(hbox);
		
		//��������
		try {
			myDictionary=new DictionaryData("dictionary.txt");
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return;
		}
		
		//�¼�����
		btSearch.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e){
				Entry entry_match=myDictionary.findEntry(tfInputBox.getText());
				taOutputBox.setText(entry_match.toString());
				if (entry_match.getID()==0)                           // ��δ�ҵ��õ���
				{ 
					String similarWords=myDictionary.findSimilarWord(tfInputBox.getText());
					taAssociationBox.setText(similarWords);
					String[] smlWord=similarWords.split("\n");
					String theMostSimilarWord=smlWord[0];
					taOutputBox.setText("���ǲ���Ҫ����"+theMostSimilarWord+"���ʣ�");
				}
			}
		}); 
		
		//��������������  
		tfInputBox.textProperty().addListener(new InvalidationListener(){
			public void invalidated(Observable ov){
				taAssociationBox.setText(myDictionary.findAssociationWord(tfInputBox.getText())); 
			}
		});
		
		//����һ������scene������������stage��
		Scene scene = new Scene(borderPane,400,300);
		primaryStage.setTitle("*****Dictionary*****");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
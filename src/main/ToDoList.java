package main;

import javafx.scene.control.DatePicker;

public class ToDoList{
	
		private int todoList;
		private DatePicker datatodoList;
		private String teksti;
		
		public ToDoList(int todoList,DatePicker datatodoList,String teksti){
			this.todoList = todoList;
			this.datatodoList = datatodoList;
			this.teksti = teksti;
		}
		
		public int getToDoList(){
			return todoList;
		}
		public DatePicker getDatatoDoList(){
			return datatodoList;
		}
		public String getTeksti(){
			return teksti;
		}
		
		public void setTodoList(int todoList){
			this.todoList = todoList;
		}
		public void setDatatoDoList(DatePicker datatodoList){
			this.datatodoList = datatodoList;
		}
		public void  setTeksti(String teksti){
			this.teksti = teksti;
		}
		
		//me von e shoh per String toSring()..
}

			
			

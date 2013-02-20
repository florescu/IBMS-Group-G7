class Validator{

  //Check if the IDs consist of numeric characters only
  public static boolean isNumeric(String paramString){

    try{
       Integer.parseInt(paramString);
       return true;
    } catch(Exception localException){
      return false; 
    }//catch

  }//main
}//class

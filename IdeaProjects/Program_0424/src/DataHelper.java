public class DataHelper {

    private static Vaus _vaus;

    public static Vaus getVaus(){
        if(_vaus == null ) throw  new IllegalStateException("Vaus is null");
        return _vaus;
    }
    public static void setVaus(Vaus vaus){
        _vaus = vaus;
    }
}

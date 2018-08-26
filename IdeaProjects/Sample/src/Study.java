class Button {
    private OnclickListener listener;

    public void setOnClickListener(OnclickListener listener) {
        this.listener = listener;
    }
}

class View {

}

interface OnclickListener {
    void onClick(View view);
}

public class Study {
    public static void main(String[] args) {
        Button button = new Button();
        button.setOnClickListener(new OnclickListener() {
            @Override
            public void onClick(View view) {
                //..동작
            }
        });

        button.setOnClickListener(view -> {
            //...동작
        });
    }

}
각 view의 id가 없으면 상대적 위치를 잡을 수 없다. 
override 는 런타임 함수 식이 본문이 함수는 컴파일타임에 타입이 추론되어 결정되는 것.
viewGroup 현재 소속되어 있ㄴㄴ viewe들의 모임.

어노테이션 런타임에 작동한다. 
annotationProcessor : 컴파일 타임에 작동하도록 만들어줄 수 있다.

parent: ViewGroup은 뭔가..


maven build script

compile 다른 모듈을 만들면 그 모듈에서 접근가능. 
dependency 가 많아질수록 빌드속도가 느려짐

Fragment

Fragment을 통해 화면을 전화하고 싶을 때에는, 
Fragment가 들어갈 layout을 잡아준 뒤에 그 영역에 fragment를 add하거나 replace해주어야 한다.

add와 replace의 차이점을 서술하기.

support library의 Fragment와
app의 Fragment의 차이점을 서술하기. 


fragmentManager -> android.app.Fragment를 인자로 받고,
supportFragmentManager -> android.support.v4.app.Fragment를 인자로 받는다.
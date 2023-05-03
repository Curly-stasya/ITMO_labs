public class ListString {
    private static class StringItem {
        private final static int SIZE = 16;//размер символьного массива
        private char[] symbols;//символьный массив
        private StringItem next;//следующий узел списка
        private byte size;//заполненность символьного массива

        public char[] getSymbols() { return symbols;}

        //конструктор по умолчанию
        public StringItem() {
            //1.next <- null
            //2.size <- 0
        }

        //конструктор узла из строки, длина которой <= SIZE, для конструктора ListString из String, добавления
        //используется только при преобразовании string к ListString => блоки еще будут полными(кроме,быть может,последнего)
        public StringItem(String str){
            //1.Преобразование строки к массиву с помощью вызова toCharArray().
                //Если пришел кусочек < 16, то
                    //создаю массив
                    //посимвольно копирую в него через charAt в цикле по длине str
            //3.next <- null
            //4.size <- длина str
        }

        //универсальный копирующий конструктор - создает копию, перенося символы, начиная со start и до end
        //параметр start используется для вырезки
        public StringItem(StringItem copied, int start, int end) {
            //1.symbols = copied.arrayCopy(copied.symbols,start,end)
            //2.next=null
            //3.size=end-start+1
        }

        //конструктор копирования если дублируется весь узел
        public StringItem(StringItem copied) {
            //вызов StringItem(copied,0,copied[size])
        }

        private StringItem unionItem(){
            //1. Копирую второй массив в первый, начиная с его позиции [size] через arrayCopy(char[]copied, int start, int end)
            //2. В поле next первого помещаю next второго те исключаю второй блок из списка
        }

        private StringItem splitItem(int index){//индекс элемента массива, после которого происходит разрез
            //1. Создаю новый узел-копию текущего узла от index+1 до его size
            //2. size текущего <- index+1
            //3. next созданной в п.1 копии <- next текущего,  next текущего <- созданная копия
        }

        public String toString(){
            String res = new String(symbols, 0, size);
            return res+'\n';
        }

        private char[] arrayCopy(char[]copied, int start, int end){//start и end включительно
            //1. Выделение памяти под новый массив char[] размера SIZE
            //2. В цикле от start до end посимвольно копирую старый массив в новый
        }

        private static class Address{
            StringItem item;
            int index;

            //конструктор адреса по найденному узлу и индексу символа внутри него
            private Address(StringItem externalAddress, int internalAddress){}
        }

    }

    //------------------------------------------------------------//
    private StringItem head;

    //конструктор по умолчанию
    public ListString(){
        //head=null;
    }
    //конструктор копирования - используется для копирования параметров метода вставки(не можем вставлять объект-параметр, нужна его копия)
    public ListString(ListString list){
        //1. узнаем длину параметра list, если 0, то  возвращаю новый объект ListString, созданный через дефолтный конструктор, если длина не 0, то действия далее
        //2. завожу итератор, который будет бегать вместо head потому что он не может перемещаться
        //3. отдельно создаю копию head. В его next создаю новый узел(копирование)
        //4. итератор переместила на следующий, в его next так же создала новый узел(копирование)
        //5. для последнего узла next<-null
    }
    public ListString(String str){
        //1. проверяю на пустоту, если str пустая, то возвращаю новый объект ListString, созданный через дефолтный конструктор
        //1. узнаем количество целых блоков через length/SIZE. Если целых 0, то переходим к п.4
        //2. создаем head
        //3. в цикле делим строку на кусочки по SIZE:
            //в цикле перебираем начальный и конечный индексы метода String substring(int beginIndex, int endIndex) через 16
            // создаем новые блоки в next'e старого - конструктор от строки
        //4. оставшийся хвостик приводим к узлу отдельно, используя конструктор от строки
    }


    //--------------------/  методы  /-----------------------//

    //реальная длина строки
    public int length(){
        //1.Если isEmpty вернул true, то возвращаю 0
        //Действия далее над непустым списком:
        //2.Если список из 1 узла те isFromOneItem() вернул true, то возвращаю head.size
        //Действия далее над списком длины >1
        //2.Иду по всем узлам до последнего - endOfString().item и уплотняя строку
        //4.Уплотнение строки:
            //
            // Начиная с первого узла,смотрю на следующий, пока сумма их реальных длин <=SIZE или следующий не null вызоваю unionItem()
            // После выхода из цикла перехожу к следующему узлу - посмотрела поле next узла, на котором вышла
    }

    //вернуть символ в строке в позиции index
    public char charAt(int index){
        //1.(в блоке try) вызов вспомогательного метода distinationItem(int index) для получения нужного узла списка(внешнего адреса) и индекса внутри него(внутреннего адреса)
        //2.Если пришло (null, -1), то выбрасываю исключение, иначе
        //3.Если в списке 1 блок те вернулось (null, index), то возвращаю символ на позиции (head, index) те head.getSymbols()[index], иначе
        //4.Если в списке больше 1 блока те вернулось конкретный адрес, то возвращаю символ с тем же индексом, но в следующем узле через next.getSymbols()[пришедший индекс]
    }

    //заменить в строке символ в позиции index на символ ch
    public void setCharAt(int index, char ch){
        //1.Вызов вспомогательного метода distinationItem(int index) для получения нужного узла списка(внешнего адреса) и индекса внутри него(внутреннего адреса)
        //2.Если пришло (null, -1), то выбрасываю исключение, иначе
        //3.Если в списке 1 блок те вернулось (null, index), то заменяю символ на позиции (head, index) те head.getSymbols()[index] на нужный символ ch, иначе
        //4.Если в списке больше 1 блока те вернулось конкретный адрес, то заменяю символ с тем же индексом, но в следующем узле через next.getSymbols()[пришедший индекс]
    }

    //взятие подстроки, от start до end, не включая end, возвращается новый объект ListString, исходный не изменяется
    public ListString substring(int start, int end) {
        //1.Если строка пустая те isEmpty()==true,выбрасываю исключение new StringException("try to substring from empty string")
        //2.Проверяю start на действительность(с помощью distinationItem находим в каких узлах расположены элементы с номерами start и end),
            //Если start (item==null & index==-1) выброс исключения StringException("invalid index")
        //3.Проверяю адрес end.
            //Если (index != -1), то идем до end, иначе до конца те end_index=length()
        //4.Создаем новый экземпляр класса ListString
        //Если start и end НЕ лежат в 1 блоке:
            //5.Создаем 2 узла через копирующий конструктор узлов, следующих за найденными в п.3  учитывая внутренний адрес - границы копирования.
                //Т.е первый копирую от индекса, полученного в адресе start до size, последний от 0 до индекса полученного из адреса end - 1
            //6.В head нового списка закидываем узел созданный от start.
            //7.создаем копию следующего узла i, в его поле next помещаем новый объект StringItem j
            //8.Пока i.next не последний (те следующий за узлом, полученным из адреса end):
                //j.next=new StringItem(i.next)
                //Передвигаемся
                //j=j.next
                //i=i.next
            //9.Связываю копии первого и последнего узла с куском между через поля next
        //Если start и end лежат в 1 блоке:
            //создаем копию узла new StringItem(узел, следующий за тем где start и end, start, end)
        //На выходе новый экземпляр - вырезанный кусочек, изначальная строка не изменена
    }

    //добавить в конец строки символ (в конец символьного массива последнего блока, если там есть свободное место, иначе в начало символьного массива нового блока)
    public void append(char ch){
        //1. Нахожу последний узел через endOfString().
        //2.Если его size < SIZE, то вставляю символ в массив на позицию [size]
        //Инче те если последний узел полностью заполнен:
                //в next'e последнего узла создаю новый узел
                //Инициализирую массив -  на позицию [0] нового массива закидываю нужный символ
                //next нового узла null
    }

    //добавить в конец строку ListString (перекинуть указатель на следующий последнего блока исходной строки на голову добавляемой строки)
    public void append(ListString string){
        //1.Делаем копию string, дальше работаем только с копией
        //2.Находим последний узел текущего списка endOfString(), его next <- head копии
    }

    //добавить в конец строку String (перекинуть указатель на следующий последнего блока исходной строки на голову добавляемой строки)
    public void append(String string){
        //1. Преобразуем string в ListString
            // new ListString(string)
        //2. Находим последний узел текущего списка endOfString(), его next <- head части, которую нужно добавить
    }

    //вставить в строку в позицию index строку ListString (разбить блок на два по позиции index и строку вставить между этими блоками)
    public void insert(int index, ListString string){
        //1. Создаю копию string - string_copy, далее использую только ее
        //2. Нахожу адрес по index(узел и внутренний адрес). Возможные ситуации
        //3. insertWithoutCheck(index, копия string)
    }

    //вставить в строку в позицию index строку String (разбить блок на два по позиции index и строку вставить между этими блоками)
    public void insert(int index, String string) {
        //проверка на пустоту- если isEmpty()==true, тогда выбрасываю исключение new StringException("trying to insert in empty string")
        //1. преобразую string в ListString, далее действия, аналогичные методу выше
        //2. находим адрес по index(узел и внутренний адрес).  Проверяю адрес на действительность
        //Если все ок, то см. дальше, иначе (item==null & index==-1) выброс исключения StringException("invalid index")
        //insertWithoutCheck(index, преобразованный string)
    }

    private void insertWithoutCheck(int index, ListString str){
        //Возможные ситуации
        //1. Вставка в начало - если адресс index (null,0):
            // Конец вставляемой части связываю с началом списка
            // string_copy.endOfString().next = this.head
        //2. Вставка в конец - если адрес index (endOfString, size-1):
            //this.append(string_copy)
        //3. Вставка за пределы строки(не валидный индекс) - если адрес (null, -1):
            //выброс исключения StringException("invalid index")
        //4. Вставка на границе узлов - если адрес.index==size(вставка в последний элемент блока)
            //все новые блоки вставляю между, никакие блоки не разбиваю
            //конец вставляемой части связываю с блоком, следующим за местом разбиения str.endOfString.item.next = адрес.item.next.next
            //блок перед местом разбиения связываю с началом вставляемой части адрес.item.next.next=str.head
        //5. Обычный случай
            // разбиваю узел splitItem(index)
            // в next последнего узла вставляемой части помещаю созданный при разбиении узел(вторую половинку разбиения)
            // в next исходного узла разбиения (первой половинки) помещаю  head части, которую необходимо вставить
    }


    //Строковое представление объекта ListString (переопределить метод)
    public String toString(){
        //1. Вызов length() для уплотнения
        //2. Создаю строку для результата
        //3. Иду по каждому узлу, пока не встречу последний прибавляю к результирующей строке каждый узел(toString переопределен для узла)
    }

    private StringItem.Address distinationItem(int index) {
        //1.Если index<0? возвращаю (null, -1)
        //2.Если список нулевой (isEmpty==true), то (null, -1), иначе далее
        //2.Если первый узел является последним (head.next==null), то возвращаю null и  index (если index в head), иначе (null, -1)
        //3.Завожу 2 итератора StringItem через дефолтный
        //4.Создаю счетчик = 0 - глобальный номер текущего символа
        //5.Первый iter1=head.next , второй iter2=head(на этом этапе уже гарантированно 2 и более узлов)
        //6.Пока iter1 не равен null
            //смотрю на size iter1 если (index - счетчик) меньше size узла 1го итератора
            //счетчик+=size
            //передвигаю итераторы iter2=iter1; iter1=iter1.next
            //условие "(index - счетчик) < size" выполнилось = нашли целевой узел
        //7.Как только первый нашел нужный узел, возвращаю второй те новый экземпляр класса Address( iter2, index-счетчик), иначе index не действительный,
            //тогда возвращаю (null, -1)
        //}
    }
    private StringItem.Address еndOfString() {//возвращает адрес 0го элемента в массиве последнего узла
        //1.Если список нулевой (isEmpty==true), то выбрасываю исключение, иначе далее
        //2.Если первый узел является последним (head.next==null), то возвращаю Address(null,0) (0 - индекс в head)
        //3.Завожу 2 итератора StringItem через дефолтный
        //4.Первый iter1=head.next , второй iter2=head
        //5.Пока iter1 не равен null,увеличиваю итераторы +1
            // iter2=iter1
            // iter1=iter1.next)
        //6.Как только iter1 нашел узел следующий за последним, возвращаю второй те новый экземпляр класса Address( iter2,0)}

    private boolean isEmpty(){
        if (this.head==null)
            return true;
        return false;
    }
    private boolean isFromOneItem(){
        if ((isEmpty()==false) && (head.next==null))
            return true;
        return false;
    }
}


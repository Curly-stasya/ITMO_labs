public class ListString {
    private static class StringItem {
        private final static int SIZE = 16;//размер символьного массива
        private char[] symbols;//символьный массив
        private StringItem next;//следующий узел списка
        private byte size;//заполненность символьного массива

        //конструктор по умолчанию
        public StringItem() {
            //память под массив выделятся по необходимости уже после создания объекта
            next=null;
            size=0;
        }

        //конструктор копирования- дублирование всего узла
        public StringItem(StringItem copied) {
            symbols= new char[SIZE];
            arrayCopy(symbols,copied.symbols,0, copied.size-1);
            next=null;
            size=copied.size;
        }

        private void unionItem(){
            //1. Переношу символы второго массива в первый, начиная с его позиции [size]
            //2. изменяю размер
            //3. В поле next первого помещаю next второго те исключаю второй блок из списка

            for(int i=0; i<next.size; i++){
                this.symbols[this.size+i]=next.symbols[i];
            }
            this.size+=next.size;
            this.next=next.next;
        }

        private void splitItem(byte index){//индекс элемента массива, после которого происходит разрез
            //1. Создаю новый узел-копию текущего узла от index до его size-1
            //2. size нового this.size-index-1, size текущего <- index,
            //3. next созданной в п.1 копии <- next текущего,  next текущего <- созданная копия
            StringItem newItem = new StringItem();
            newItem.symbols=new char[StringItem.SIZE];
            arrayCopy(newItem.symbols,this.symbols, index, this.size-1);

            newItem.size=(byte) (this.size-index);
            this.size=index;

            newItem.next=this.next;
            this.next=newItem;
        }

        public String toString(){
            String res = new String(symbols, 0, size);
            return res;
        }
        private static class Address{
            StringItem item;
            byte index;

            //конструктор адреса по найденному узлу и индексу символа внутри него
            private Address(StringItem externalAddress, byte internalAddress){
                item=externalAddress;
                index=internalAddress;
            }
        }
    }


    //------------------------------------------------------------//
    private StringItem head;

    //конструктор по умолчанию
    public ListString(){
        head=null;
    }

    //конструктор копирования - используется для копирования параметров метода вставки(не можем вставлять объект-параметр, нужна его копия)
    public ListString (ListString list){ // A -> B
        //1. Если list пустой, то  возвращаю новый объект ListString, созданный через дефолтный конструктор, если нет, то действия далее
        //2. отдельно создаю копию head. В его next создаю новый узел(копирование)
        //3. завожу 2 итератора, 1 в исходном, другой в новом будующем списке
        //4. пока 1 итератор не дойдет до конца исходного списка
            // в новом создаю узлы-копии
            // перемещаю итераторы
        //5. для последнего узла next<-null

        if (list.head==null){//параметр - пустой список
            head=null;
        }
        else{
            head=new StringItem(list.head);

            StringItem iterA =list.head.next;//итератор в исходном списке
            StringItem iterB =head;//итератор в новом списке
            while (iterA !=null){
                iterB.next=new StringItem(iterA);
                iterA = iterA.next;
                iterB = iterB.next;
            }
            iterB.next=null;
        }
    }
    public ListString(String str){
        //1. проверяю на пустоту, если str пустая, то head null
        //2. узнаем количество целых блоков через length/SIZE. Если целых 0, то переходим к п.6
        //3. создаем head
        //4. в цикле делим строку на кусочки по SIZE:
            //в цикле перебираем начальный и конечный индексы метода String substring(int beginIndex, int endIndex) через 16
            //получившийся кусочек преобразуем к массиву с помощью вызова toCharArray()
            //создаем новые блоки в next'e старого через дефолтный, в symbols закидываю полученный массив
        //5. оставшийся хвостик приводим к узлу отдельно, используем метод копирования массива
            //создаю новый блок, в его symbols посимвольно копирую через charAt в цикле по длине кусочка
        //6. всю строку приводим к массиву, через toCharArray, полученный массив закидываем в head
        if(str.equals("")){
            head=null;
        }
        else{
            int wholeItemsNum=str.length()/StringItem.SIZE;//количество целых кусочков по 16
            if (wholeItemsNum!=0){
                head=new StringItem();
                head.symbols=str.substring(0,StringItem.SIZE).toCharArray();
                head.size=StringItem.SIZE;
                head.next=new StringItem();
                StringItem iter=head;
                for(int i=1; i< wholeItemsNum; i++){//иду по каждому кусочку
                    iter.next.symbols=(str.substring(i*StringItem.SIZE, (i+1)*StringItem.SIZE).toCharArray());
                    iter.next.size=StringItem.SIZE;
                    iter.next.next=new StringItem();
                    iter=iter.next;
                }
                //приводим оставшийся кусочек
                iter.next.symbols = new char[StringItem.SIZE];
                int s=str.substring(wholeItemsNum*StringItem.SIZE).length();
                arrayCopy(iter.next.symbols,str.substring(wholeItemsNum*StringItem.SIZE).toCharArray(), 0, s-1 );
                iter.next.size=(byte) s;
            }
            else{//вся строка помещается в 1 узел списка
                head=new StringItem();
                head.symbols=str.toCharArray();
                head.size= (byte) str.length();
            }
        }
    }


    //--------------------/  методы  /-----------------------//

    //реальная длина строки
    public int length(){
        //1.Если строка пустая, то возвращаю 0. Действия далее над непустым списком:
        //2.Завожу итератор, изначально установленный на head
        //3.Иду по всем узлам до последнего те пока указатель не станет null
            //4. пока есть возможность те пока сумма размеров соседних узлов < 16 или пока мы не дойдет до конца - объединяю блоки- вызоваю unionItem()
            //добавляю к общей сумме размер получившегося узла
        //5.если узел уплотнен по максимуму и следующий уже не помещается, то переходим в глобальном цикле к следующему
        if (head==null){
            return 0;
        }
        else{
            int res=0;
            StringItem iter = head;
            while (iter!=null){
                while((iter.next!=null) && (iter.size + iter.next.size <= StringItem.SIZE)){
                    iter.unionItem();
                }
                res+=iter.size;

                if (iter.next!=null)//те текущий узел по максимуму объединили
                    iter=iter.next;
                else return res;//те дошли до конца списка
            }
            return res;
        }
    }

    //вернуть символ в строке в позиции index
    public char charAt(int index){
        //1.Обрабатываем ситуация пустой строки и заведомо невалидного индекса
        //2.вызов вспомогательного метода distinationItem(int index) для получения нужного узла списка(внешнего адреса) и индекса внутри него(внутреннего адреса)
        //3.Если пришло (null, -1), то выбрасываю исключение, иначе
        //4.Если в списке 1 блок те вернулось (null, index), то возвращаю символ на позиции (head, index) те head.symbols[index], иначе
        //5.Если в списке больше 1 блока те вернулось конкретный адрес, то возвращаю символ с тем же индексом, но в следующем узле через next.symbols()[пришедший индекс]
        if(head==null)
            throw new StringException("try to get char from empty string");
        if (index<0) throw new StringException("invalid index");

        StringItem.Address address = distinationItem(index);

        if(address.item==null){
            if (address.index==-1) throw new StringException("invalid index");
            else return head.symbols[index];
        }

        else return address.item.next.symbols[address.index];
    }

    //заменить в строке символ в позиции index на символ ch
    public void setCharAt(int index, char ch){
        //1.Обрабатываем ситуация пустой строки и заведомо невалидного индекса
        //2.Вызов вспомогательного метода distinationItem(int index) для получения нужного узла списка(внешнего адреса) и индекса внутри него(внутреннего адреса)
        //3.Если пришло (null, -1), то выбрасываю исключение, иначе
        //4.Если в списке 1 блок те вернулось (null, index), то заменяю символ на позиции (head, index) те head.symbols()[index] на нужный символ ch, иначе
        //5.Если в списке больше 1 блока те вернулось конкретный адрес, то заменяю символ с тем же индексом, но в следующем узле через next.symbols()[пришедший индекс]
        if(head==null)
            throw new StringException("try to set char from empty string");
        if (index<0)
            throw new StringException("invalid index");

        StringItem.Address address = distinationItem(index);
        if(address.item==null) {
            if (address.index == -1) throw new StringException("invalid index");
            else head.symbols[index] = ch;
        }
        else address.item.next.symbols[address.index]=ch;
    }

    //взятие подстроки, от start до end, не включая end, возвращается новый объект ListString, исходный не изменяется
    public ListString substring(int start, int end) {
        //1.Выброс исключений при ситуации пустого списка, заведомо невалидного начального/конечного индекса, невалидной пары индексов когда начальная позиция больше конечной
        //2. Если границы вырезки совпадают, то никак не изменяем текущий список
        //3. Нахожу адреса start, end:
            //с помощью distinationItem находим в каких узлах расположены элементы с номерами start и end
            //определяем фактический адрес начальной позиции, помним о том, что метод distinationItem() возвращает предыдущий узел
            //определяем фактический адрес начальной позиции аналогично старту, но с условием если адрес невалидный, вырезка производится до конца строки
        //4.Создаем новый экземпляр класса ListString
        //Если start и end НЕ лежат в 1 блоке:
            //head нового списка - копия узла реальной начальной позиции, учитывая внутренний адрес - границы копирования.
            //с помощью 2 итераторов копирую узлы между стартовой и конечной позицией вырезки
            //создаем копию следующего узла i, в его поле next помещаем новый объект StringItem j
            //Пока i.next не последний (те следующий за узлом, полученным из адреса end):
            //j.next=new StringItem(i.next)
            //Передвигаемся
                //j=j.next
                //i=i.next
            //Последний узел списка - узел копия блока реального адреса конечной позиции с учетом границ копирования
        //Если start и end лежат в 1 блоке:
            //создаем head нового списка, его символьный массив - копия узла, общего для начала и конца, между внутренними индексами стартовой и конечной позиций

        //На выходе новый экземпляр - вырезанный кусочек, изначальная строка не изменена
        if (head == null) throw new StringException("try to substring from empty string");

        if (start<0)
            throw new StringException("invalid start index");
        if (end<0)
            throw new StringException("invalid end index");
        if (start>end)
            throw new StringException("invalid indexes, start index > end index");

        if(start==end) return this;

        StringItem.Address startAddress = distinationItem(start);
        StringItem.Address endAddress = distinationItem(end);
        StringItem realEndItem;
        StringItem realStartItem;

        //определяем фактический узел стартовой позиции
        if (startAddress.item == null && startAddress.index == -1) throw new StringException("invalid start index");
        if (startAddress.item == null) realStartItem = this.head;
        else realStartItem=startAddress.item.next;

        //определяем фактический узел конечной позиции
        if (endAddress.index == -1){
            realEndItem = endOfString();
            endAddress.index=(byte) (endOfString().size-1);
        }
        else if (endAddress.item == null)
            realEndItem = this.head;
        else
            realEndItem = endAddress.item.next;


        ListString res= new ListString();
        res.head=new StringItem();
        res.head.symbols=new char[StringItem.SIZE];

        if (realStartItem!=realEndItem){//стартовая и конечная позиции в разных узлах
            arrayCopy(res.head.symbols, realStartItem.symbols, (byte) startAddress.index,realStartItem.size-1);
            res.head.size=(byte)(realStartItem.size-startAddress.index);

            StringItem iterA=realStartItem.next;//итератор в исходном списке
            StringItem iterB=res.head;//итератор в результирующем списке (отстает на 1 позицию)
            while (iterA!=realEndItem) {
                iterB.next = new StringItem(iterA);
                iterA = iterA.next;
                iterB = iterB.next;
            }

            iterB.next=new StringItem();
            iterB.next.symbols=new char[StringItem.SIZE];
            arrayCopy(iterB.next.symbols, realEndItem.symbols, (byte) 0,endAddress.index);
            iterB.next.size = (byte) (endAddress.index+1);
        }
        else{//стартовая и конечная позиции лежат в одном узле списка
            arrayCopy(res.head.symbols, realStartItem.symbols, startAddress.index, (byte) (endAddress.index-1));
            res.head.size = (byte) (endAddress.index-startAddress.index);
        }
        return res;
    }

    //добавить в конец строки символ (в конец символьного массива последнего блока, если там есть свободное место, иначе в начало символьного массива нового блока)
    public void append(char ch){
        //1. Если список пустой, то создаю head, в 0 позицию его символьного массива помещаю новый символ, изменяю размер. Иначе
        //2. Нахожу последний узел через endOfString().
            //3.Если его size < SIZE, то вставляю символ в массив на позицию [size], изменяю размер
            //4. Иначе те если последний узел полностью заполнен:
                    //в next'e последнего узла создаю новый узел
                    //Инициализирую массив -  на позицию [0] нового массива закидываю нужный символ
                    //изменяю размер
                    //next нового узла null

        if(head==null){//список пустой
            head=new StringItem();
            head.symbols=new char[StringItem.SIZE];
            head.symbols[0]=ch;
            head.size=1;
        }
        else {
            StringItem end=endOfString();
            if (end.size < StringItem.SIZE)
                end.symbols[end.size] = ch;
            else {
                end.next = new StringItem();
                end.next.symbols[0] = ch;
            }
            end.size++;
        }
    }

    //добавить в конец строку ListString (перекинуть указатель на следующий последнего блока исходной строки на голову добавляемой строки)
    public void append(ListString string){
        //1.Если строка пустая, ничего не изменяю
        //2.Делаем копию string, дальше работаем только с копией
        //3.Если текущий список пустой, то head<-head копии. Иначе
        //4.Находим последний узел текущего списка endOfString(), его next <- head копии
        if (string.head==null)
            return;
        ListString str = new ListString(string);
        if(head==null)
            this.head=str.head;
        else if (str.head!=null)
            this.endOfString().next=str.head;
    }

    //добавить в конец строку String (перекинуть указатель на следующий последнего блока исходной строки на голову добавляемой строки)
    public void append(String string){
        //1.Если строка пустая, ничего не изменяю
        //2. Преобразуем string в ListString
        //3.Если текущий список пустой, то head<-head копии. Иначе
        //4.Находим последний узел текущего списка endOfString(), его next <- head части, которую нужно добавить
        if (string.equals(""))
            return;

        ListString str=new ListString(string);
        if(head==null)
            this.head=str.head;
        else
            this.endOfString().next=str.head;
    }

    //вставить в строку в позицию index строку ListString (разбить блок на два по позиции index и строку вставить между этими блоками)
    // символ в позиции index остается после вставленной части
    public void insert(int index, ListString string){
        //1. Если список пустой или индекс заведомо невалидный, то  выбрасываю исключение
        //Изменения вношу, если вставляемая строка не пустая
        //2. Создаю копию string - string_copy, далее использую только ее
        //3. Нахожу адрес по index(узел и внутренний адрес), если такого индекса найдено не было, то выбрасываю исключение
        //4. insertWithoutCheck(index, копия string)
        if (index<0) throw new StringException("invalid index");

        if (this.head==null) throw new StringException("trying to insert in empty string");

        if (string.head!=null) {//вставляем не пустую строку
            StringItem.Address address = distinationItem(index);
            if (address.item == null && address.index == -1)
                throw new StringException("invalid index");
            else {
                ListString stringCopy = new ListString(string);
                insertForCopies(address, stringCopy);
            }
        }
    }

    //вставить в строку в позицию index строку String (разбить блок на два по позиции index и строку вставить между этими блоками)
    public void insert(int index, String string) {
        //1. Если список пустой или индекс заведомо невалидный, то  выбрасываю исключение
        //Изменения вношу, если вставляемая строка не пустая
        //2. преобразую string в ListString, далее действия, аналогичные методу выше
        //3. находим адрес по index(узел и внутренний адрес), если такого индекса найдено не было, то выбрасываю исключение
        //4. insertWithoutCheck(index, преобразованный string)
        if (index<0) throw new StringException("invalid index");

        if (this.head==null) throw new StringException("trying to insert in empty string");

        if(!string.equals("")) {
            StringItem.Address address = distinationItem(index);
            if (address.item == null && address.index == -1)
                throw new StringException("invalid index");
            else {
                ListString stringCopy = new ListString(string);
                insertForCopies(address, stringCopy);
            }
        }
    }

    private void insertForCopies(StringItem.Address address, ListString str){//вспомогательный метод
        //Возможные ситуации
        //1. Вставка в начало - если адресс index (null,0):
            // Конец вставляемой части связываю с началом списка
            // head <- str.head
        //2. Вставка за пределы строки(не валидный индекс) - если адрес (null, -1):
            //выброс исключения StringException("invalid index")
        //3. Обычный случай
            // разбиваю узел splitItem(index)
            // в next последнего узла вставляемой части помещаю созданный при разбиении узел(вторую половинку разбиения)
            // в next исходного узла разбиения (первой половинки) помещаю  head части, которую необходимо вставить
        //4. Вставка на границе узлов - если адрес.index==size(вставка в последний элемент блока)
            //все новые блоки вставляю между, никакие блоки не разбиваю
            //конец вставляемой части связываю с блоком, следующим за местом разбиения str.endOfString.item.next = адрес.item.next.next
            //блок перед местом разбиения связываю с началом вставляемой части адрес.item.next.next=str.head

        if (address.item==null && address.index==0){//Вставка в начало
            str.endOfString().next = this.head;
            this.head=str.head;
        }
        else {
            if (address.item==null)// адрес возвращает предыдущий узел списка
                address.item=head;

            if (address.index!=0){//Обычный случай - вставка внутри блока - вероятнее вставки на границе
                address.item.splitItem(address.index);
                str.endOfString().next = address.item.next;
                address.item.next=str.head;
            }
            else{//Вставка на границе узлов
                str.endOfString().next=address.item.next;
                address.item.next=str.head;
            }
        }
    }
    //Строковое представление объекта ListString (переопределить метод)
    public String toString(){
        //1. Вызов length() для уплотнения
        //2. Создаю строку для результата
        //3. Иду по каждому узлу, пока не встречу последний прибавляю к результирующей строке каждый узел(toString переопределен для узла)
        //this.length();
        String res="";
        StringItem iter = head;
        while (iter!=null){
            res+=iter;//toString переопределен для узла
            iter=iter.next;
        }
        return res;
    }
    private StringItem.Address distinationItem(int index) {
        //4.Создаю счетчик = 0 - глобальный номер текущего символа
        //5.Создаю 2 итератора - первый iter1=head , второй iter2=null
        //6.Пока iter1 не равен null
            //смотрю на size iter1, если (index - счетчик) >= iter1.size, то
            //вклчаем этот узел в счет - счетчик+=iter1.size
            //передвигаю итераторы iter2=iter1; iter1=iter1.next
            //условие "(index - счетчик) < size" выполнилось = нашли целевой узел
        //6. Как только первый дошел до конца, значит index не действительный, тогда возвращаю (null, -1)
        //7. Иначе первый нашел нужный узел, возвращаю второй те новый экземпляр класса Address( iter2, index-счетчик)

        int counter=0;
        StringItem iter1= head;
        StringItem iter2 = null; //чтобы вернуть предыдущий узел

        while (iter1!=null && index-counter>=iter1.size){
            counter+=iter1.size;
            iter2=iter1;
            iter1=iter1.next;
        }
        if (iter1==null)
            return new StringItem.Address(null, (byte)(-1));//такой индекс не нашелся
        else {//те вышли из цикла по второму условию
            return new StringItem.Address(iter2, (byte) (index-counter));
        }
    }
    private StringItem endOfString() {//возвращает последний узел
        //1.Завожу 2 итератора StringItem первый iter1=head.next , второй iter2=head
        //2.Пока iter1 не дойдет до конца,увеличиваю итераторы +1
            // iter2=iter1
            // iter1=iter1.next
        //3.Как только iter1 нашел узел следующий за последним, возвращаю iter2

        StringItem iter1= head.next;
        StringItem iter2 = head;

        while (iter1!=null){
            iter2=iter1;
            iter1=iter1.next;
        }
        return iter2;
    }
    private static void arrayCopy(char[]array, char[]copied, int start, int end){//start и end включительно
        //В цикле от start до end посимвольно копирую один массив в другой;
        for(int i=start,j=0;i<end+1; i++,j++){
            array[j]=copied[i];
        }
    }
}

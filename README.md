# 1. Logger
  Проект представляет из себя классический логгер, который будет выводить необходимые пользователю сообщения. Логгер является довольно гибким, так как имеет множество режимов,       которые можно комбинировать:
  ### - в первую очередь пользователь должен понимать, куда он хочет выводить сообщения, поэтому данный логгер обладает тремя режимами вывода, а именно 
  ```Java
    public enum TypeOfOutput {
        FILE,
        CONSOLE,
        BOTH
    }
  ```
  **FILE** - говорит о том, что сообщения выводятся в файл (если такового не существует, то логгер сам создаст его). Также можно указать путь к своему собственному файлу с помощью соответствующего сеттера 
  ```Java
    public void setFileName(String fileName);
  ```
  **CONSOLE** - говорит о том, что сообщения выводятся в консоль.
  
  ![image](https://user-images.githubusercontent.com/49618499/121579805-bd6bc980-ca34-11eb-84a5-b54ea7328ea0.png)
  
  **BOTH** - сообщения выводятся как в консоль, так и в файл.
  
  ### - во вторую очередь пользователю важно, как именно выводятся сообщения - сразу же по сигналу или накопительным образом. Для этого есть два режима:
  ```Java
    public enum Mode {
        ACCUMULATION,
        STRAIGHT
    }
  ```
  **STRAIGHT** - прямое логгирование "as-is", передаем логгеру сообщение и он его выводит.
  
  **ACCUMULATION** - накопительное логгирование, т.е. пользователь передает логгеру некоторое количество сообщений, после чего дает ему сигнал о поочередном выводе этих          сообщений   с сохранением порядка. Данная схема реализована с помощью контейнера для накопления сообщений, а также "сигнального" метода flush().
  ```Java
    private List<String> accumulationList = new ArrayList<>();
    ...
    public void flush();
  ```
  
  ### - характерной особенностью каждого логгера являются, конечно же, уровни логгирования
  ```Java
    public enum Level {
        DEBUG,
        INFO,
        WARNING,
        ERROR,
        FATAL
    }
  ```
  Установить уровень логгера можно сеттером, а уровни сообщений устанавливаются с помощью методов
  ```Java
    public void debug(String message);
    public void info(String message);
    public void warning(String message);
    public void error(String message);
    public void fatal(String message);
  ```
  Непосредственно в коде уровни расположены в порядке важности, то есть если уровень логгера, например, ERROR, то сообщение с уровнем DEBUG будет проигнорировано.
  ### - данный логгер также является Singlton: реализовано это с помощью приватного конструктора, что не позволяет создавать более одного экземпляра логгера.
  #### Пример использования:
  Если не устанавливать изначально никакие режимы, то они по умолчанию выставляются так - прямой вывод в консоль с уровнем DEBUG.
  
  ![image](https://user-images.githubusercontent.com/49618499/121583404-df674b00-ca38-11eb-9c7f-70e9a025c4a5.png)
  
  В данном примере мы установили накопительное логгирование с выводом в консоль и режимом WARNING. Сообщения не выведутся, пока не будет вызван метод flush().
  Попробуем теперь залоггировать сообщение в файл 
  
  ![image](https://user-images.githubusercontent.com/49618499/121584168-d9be3500-ca39-11eb-818c-7b9bd715262c.png)

  ![image](https://user-images.githubusercontent.com/49618499/121584256-f5294000-ca39-11eb-8f30-26a467f4b516.png)

  Видим, что сообщения выводятся без вызова метода flush() (т.к. был поставлен режим прямого логгирования), а также, что создался файл log.txt, в котором вывелись все наши        сообщения, соответствуя правилам уровней.

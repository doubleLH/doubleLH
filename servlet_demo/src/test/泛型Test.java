package test;

import java.io.Serializable;

/**
 * 泛型类
 * 
 * 泛型可以防止ClassCastException 强制类行转换异常 (运行期异常/非检查行异常)
 * 泛型擦除之后是Object
 * 
 */
class GirlFriend<T,L> implements Serializable
{
	private static final long serialVersionUID = 1L;

	private String name;
     
    private Integer age;
     
     /**
      * 恋爱状态
      * 
      * 泛型字段
      */
    private T lovingTime;
    
   

	private L lovingStatus;

    
    
    public GirlFriend() {
		super();
	}
    
	public GirlFriend(String name, Integer age, T lovingTime, L lovingStatus) {
		super();
		this.name = name;
		this.age = age;
		this.lovingTime = lovingTime;
		this.lovingStatus = lovingStatus;
	}


	 
    public L getLovingStatus() {
		return lovingStatus;
	}

	public void setLovingStatus(L lovingStatus) {
		this.lovingStatus = lovingStatus;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	/**
	 * 泛型方法
	 */
	public T getLovingTime() {
		return lovingTime;
	}

	public void setLovingTime(T lovingTime) {
		this.lovingTime = lovingTime;
	}

	/**
	 * 方便测试girl中的信息(项目正式发布之前一定要注释掉)
	 */
	@Override
	public String toString() {
		return "GirlFriend [name=" + name + ", age=" + age + ", lovingTime="
				+ lovingTime + ", lovingStatus=" + lovingStatus + "]";
	}

	
}



public class 泛型Test 
{
	/**
	 * ? extends Number  限定上限 
	 *    通配符?可以是Number的子类 or Number自己   其中?叫做通配符
	 *    通配符只能读 不能写
	 */
	public static void method1(GirlFriend<? extends Number, String> gf)
	{
		System.out.println("恋爱时间:" + gf.getLovingTime());
		
		// The method setLovingTime(capture#2-of ? extends Number) in the type GirlFriend<capture#2-of ? extends Number,String> is not applicable for the arguments (int)
		// gf.setLovingTime(2);
	}
	
	/**
	 * ? extends Number  限定下限 
	 *    通配符?可以是Number自己 or Number的父类   其中?叫做通配符
	 */
	public static void method2(GirlFriend<? super Integer, String> gf)
	{
		System.out.println("恋爱时间:" + gf.getLovingTime());
		
		gf.setLovingTime(2);
		
		System.out.println("恋爱时间:" + gf.getLovingTime());
	}
	
    public static void main(String[] args) 
    {
    	// GirlFriend<String> gf = new GirlFriend<String>("如花", 18, "1年");
    	// GirlFriend<Double> gf = new GirlFriend<Double>("如花", 18, 1.5D);
    	
    	/*
    	GirlFriend<String, String> gf = new GirlFriend<String, String>("如花", 18, "1年", "本垒");
    	System.out.println(gf);
    	*/
    	
    	// GirlFriend<Integer, String> gf = new GirlFriend<Integer, String>("如花", 18, 1, "本垒");
    	// GirlFriend<Number, String> gf = new GirlFriend<Number, String>("如花", 18, 1, "本垒");
    	
    	GirlFriend<Number, String> gf = new GirlFriend<Number, String>("如花", 18, 1, "本垒");
    	method2(gf);
	}
}

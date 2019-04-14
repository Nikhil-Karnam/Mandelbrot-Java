public class Calculate {
	
	public static int i = 0;
	public static double blockReal = 0;
	public static double blockComplex = 0;
	public static int c;
	
	//returns true if the sequence  a(n) = [a(n-1)]^2 + c  converges. c is any complex number.
	public static boolean convergence(double sumA, double sumB, double a, double b)
	{		
		Double real = asquare(sumA, sumB) + a;
		Double complex = bsquare(sumA, sumB) + b;
		
		i++;
		
		//diverges
		if (magnitude(real, complex) > 4)
		{
			//to color code it and create the halo
			if(i < 255)
				c = 255-i;
						
			i = 100;
			return false;
		}

		//test for convergence. If true, unwinds the stack and repeats the process to prevent stack overflow error
		//Java can only perform 4000 iterations at a time.
		//for loop to repeat the process is in plot class.
		else if (i > 500 && magnitude(real, complex) <= 4)
		{
			i = 0;
			blockReal = real;
			blockComplex = complex;
			return true;
		}

		return convergence(real, complex, a, b);
	}
	
	
	//returns real number component of (a+bi)^2
	public static double asquare(double a, double b)
	{		
		return a*a - b*b;
	}
	
	//returns complex number component of (a+bi)^2
	public static double bsquare(double a, double b) 
	{
		return 2*a*b;
	}
	
	
	//returns magnitude of vector with components <a, b>
	public static double magnitude(double a, double b) 
	{
		return a*a + b*b;
	}
}

# Linear-Regression-And-Regularization

Sample program output. Randomly generatered training and testing datasets based around y = x^2 + 10 where -2 <= x <= 10


--TRAINING DATA SET--
Record #0 :: Y: 19  	Values:	X :: 3.0 
Record #1 :: Y: 14  	Values:	X :: -2.0 
Record #2 :: Y: 26  	Values:	X :: 4.0 
Record #3 :: Y: 46  	Values:	X :: 6.0 
Record #4 :: Y: 11  	Values:	X :: 1.0 
Record #5 :: Y: 11  	Values:	X :: 1.0 
Record #6 :: Y: 14  	Values:	X :: 2.0 
Record #7 :: Y: 91  	Values:	X :: 9.0 
Record #8 :: Y: 74  	Values:	X :: 8.0 
Record #9 :: Y: 26  	Values:	X :: 4.0 
Record #10 :: Y: 10  	Values:	X :: 0.0 
Record #11 :: Y: 14  	Values:	X :: 2.0 

--TESTING DATA SET--
Record #0 :: Y: 11  	Values:	X :: 1.0 
Record #1 :: Y: 11  	Values:	X :: -1.0 
Record #2 :: Y: 59  	Values:	X :: 7.0 
Record #3 :: Y: 46  	Values:	X :: 6.0 
Record #4 :: Y: 14  	Values:	X :: -2.0 
Record #5 :: Y: 11  	Values:	X :: 1.0 
Record #6 :: Y: 26  	Values:	X :: 4.0 
Record #7 :: Y: 35  	Values:	X :: 5.0 
Record #8 :: Y: 14  	Values:	X :: -2.0 
Record #9 :: Y: 110  	Values:	X :: 10.0 
Record #10 :: Y: 14  	Values:	X :: -2.0 
Record #11 :: Y: 59  	Values:	X :: 7.0 

Linear Regression without Regularization
y = 5.72046109510086 + 7.561959654178675x
MSE(train) = 111.70605187319886
MSE(test) = 236.5152535663171

		Lambda( 0.1 ) : In-Sample Error is ( 69.28548380360408 ) : Validation Error is ( 156.00413104701005 )
		Lambda( 1.0 ) : In-Sample Error is ( 69.93331284961883 ) : Validation Error is ( 148.37939094675284 )
		Lambda( 10.0 ) : In-Sample Error is ( 80.36621381098684 ) : Validation Error is ( 136.18039311375247 )
		Lambda( 100.0 ) : In-Sample Error is ( 237.72069006080287 ) : Validation Error is ( 281.79171827064096 )

Linear Regression with Regularization. Lambda is 10.0
y = 2.898185483870967 + 7.690524193548388x
MSE(train) = 117.6983431294442
MSE(test) = 282.01222362908646

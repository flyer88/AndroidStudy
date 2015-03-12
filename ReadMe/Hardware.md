# Hardware
#### 3-12 accelerometer和light
+ 调用Sensor通用方法
	- 获取SensorManager
`SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE)`

	- 获取Sensor
获取光照感应器
`Sensor sensor = sensorManager.getDefault(Sensor.TYPE_LIGHT)`
获取加速感应器
`Sensor sensor = sensorManager.getDefault(Sensor.TYPE_ACCELEROMETER)`
  
	- 创建 listener

			SensorEventListener listener = new 	SensorEventListener() {
        	@Override
        	public void onSensorChanged(SensorEvent event) {
            float value = event.values[0];
            lightLevel.setText(“当前光照强度为”+value+”lx”);
       	 	}
       	 	@Override
        	public void onAccuracyChanged(Sensor sensor, int accuracy) {

       	 	}
    		};
	- 注册listener
`sensorManager.registerListener（sensorEventListener,sensor,SensorManager.SENSOR_DELAY_MORMAL）`
	- 注销listener，onDestroy中调用
`sensorManager.unregisterListener（sensorEventListener）`


+ 指南正中listener的具体内容
	
		SensorEventListener listener = new SensorEventListener() {
        float[] accelerometerValues = new float[3];
        float[] magneticValues = new float[3];
        float lastRotateDegree;
        @Override
        public void onSensorChanged(SensorEvent event) {
            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            		//要调用clone
                accelerometerValues = event.values.clone();
            }else if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD){
                //要调用clone
                magneticValues = event.values.clone();
            }
            float[] R = new float[9];
            float[] values = new float[3];
            //获取旋转矩阵
            SensorManager.getRotationMatrix(R,null,accelerometerValues,magneticValues);
            //获取旋转方向
            SensorManager.getOrientation(R, values);
				//转换角度
            float rotateDegree = -(float) Math.toDegrees(values[0]);
            
            if (Math.abs(rotateDegree - lastRotateDegree)>1){
            //设置旋转动画
                RotateAnimation animation = new RotateAnimation(
                        lastRotateDegree,rotateDegree, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f
                );
                animation.setFillAfter(true);
                compass.startAnimation(animation);
                lastRotateDegree = rotateDegree;
            }

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    	};

		
    	


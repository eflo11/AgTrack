(function(){
   angular.module('app.grower.service', [])
       .factory('GrowerService', GrowerService);


    GrowerService.$inject = ['$http'];
    function GrowerService($http){
        var growerFactory = {};

        growerFactory.get = function(growerId){
           return $http.get('/rest/grower/' + growerId);
        };

        growerFactory.currentWeather = function(city, state){
            return $http.get('http://api.wunderground.com/api/fc1ca869313e0489/conditions/q/' + state + '/' +
                city + '.json');
        };

        growerFactory.hourlyWeather = function(city, state){
            return $http.get('http://api.wunderground.com/api/fc1ca869313e0489/hourly/q/' + state + '/' +
                city + '.json');
        };

        growerFactory.forecast = function(city, state){
          return $http.get('http://api.wunderground.com/api/fc1ca869313e0489/forecast10day/q/' + state + '/' +
              city + '.json');
        };

        growerFactory.widgets = function(growerId){
            return [
                {
                    col: 0,
                    row: 0,
                    sizeY: 1,
                    sizeX: 2,
                    name: "Bar Chart",
                    chart: {
                        options: {
                            chart: {
                                type: 'multiBarHorizontalChart',
                                margin : {
                                    top: 80,
                                    right: 50,
                                    bottom: 50,
                                    left: 50
                                },
                                x: function(d){return d.label;},
                                y: function(d){return d.value;},
                                //yErr: function(d){ return [-Math.abs(d.value * Math.random() * 0.3), Math.abs(d.value * Math.random() * 0.3)] },
                                showControls: true,
                                showValues: true,
                                duration: 500,
                                xAxis: {
                                    showMaxMin: false
                                },
                                yAxis: {
                                    axisLabel: 'Values',
                                    tickFormat: function(d){
                                        return d3.format(',.2f')(d);
                                    }
                                }
                            }
                        },
                        data: [
                            {
                                "key": "Apple",
                                "color": "#d62728",
                                "values": [
                                    {
                                        "label" : "Conv" ,
                                        "value" : 12
                                    } ,
                                    {
                                        "label" : "Organic" ,
                                        "value" : 8
                                    }
                                ]
                            },
                            {
                                "key": "Pear",
                                "color": "#1f77b4",
                                "values": [
                                    {
                                        "label" : "Conv" ,
                                        "value" : 2
                                    } ,
                                    {
                                        "label" : "Organic" ,
                                        "value" : 4
                                    }
                                ]
                            }
                        ],
                        api: {}
                    }
                },
                {
                    col: 2,
                    row: 0,
                    sizeY: 1,
                    sizeX: 1,
                    name: " Chart",
                    chart: {
                        options: {
                            chart: {
                                type: 'pieChart',
                                margin : {
                                    top: 80,
                                    right: 50,
                                    bottom: 50,
                                    left: 50
                                },
                                x: function(d){return d.key;},
                                y: function(d){return d.y;},
                                showLabels: false,
                                duration: 500,
                                labelThreshold: 0.01,
                                labelSunbeamLayout: true,
                                legend: {
                                    margin: {
                                        top: 5,
                                        right: 5,
                                        bottom: 5,
                                        left: 0
                                    }
                                }
                            }
                        },
                        data: [
                            {
                                key: "Pear",
                                y: 3
                            },
                            {
                                key: "Apple",
                                y: 2
                            }
                        ],
                        api: {}
                    }
                }
            ];
        };

        return growerFactory;
    }

})();

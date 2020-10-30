<template>
  <div class="wrapper">
    <div id="main" ref="echart" style="width: 600px;height:400px;"></div>
    <div>
      <i @click="changeChart('line')">改为折线图</i>
       &nbsp;
      <i @click="changeChart('bar')">改为柱状图</i>
    </div>
    <div>
      <el-row v-for="(item,index) in tableData" :key="JSON.stringify(item)">
        <el-col v-for="i in item" :key="i" :span="parseInt((24/item.length))">
          <div v-if="i==='+'">
            <b @click="changeChart('line',index)">L</b>
            &nbsp;
            <b @click="changeChart('bar',index)">B</b>
          </div>
          <template v-else>{{i}}</template>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
const echarts = window.echarts
export default {
  name: 'VChat-echart',
  components: {
  },
  props: {},
  data () {
    return {
      echart: {
        rowTitle: [
          { name: '未命名1', 'type': 'bar' },
          { name: '未命名2', 'type': 'bar' },
          { name: '未命名3', 'type': 'line' }
        ],
        colTitle: ['一', '二', '三', '四', '五'],
        data: [
          [120, 132, 101, 134, 90],
          [220, 182, 191, 234, 290],
          [150, 232, 201, 154, 190]
        ]
      },
      data: {
        echartNode: null,
        type: 'line',
        x: 0,
        y: 0,
        width: 600,
        height: 400,
        z: 0,
        angle: 0,
        obj: {
          chartType: 'line',
          legend: ['未命名1', '未命名2', '未命名3'],
          xAxis: ['一', '二', '三', '四', '五'],
          color: ['#c23531', '#2f4554', '#61a0a8'],
          data: [{
            name: '未命名1',
            type: 'bar',
            data: [120, 132, 101, 134, 90],
            itemStyle: {
              normal: {
                label: {
                  show: true
                }
              }
            }
          },
          {
            name: '未命名2',
            type: 'bar',
            data: [220, 182, 191, 234, 290],
            itemStyle: {
              normal: {
                label: {
                  show: true
                }
              }
            }
          },
          {
            name: '未命名3',
            type: 'line',
            data: [150, 232, 201, 154, 190],
            itemStyle: {
              normal: {
                label: {
                  show: true
                }
              }
            }
          }]
        }
      }
    }
  },
  watch: {
    echart: {
      handler () {
        this.drawChart()
      },
      deep: true,
    }
  },
  computed: {
    tableData () {
      let { data = [], colTitle, rowTitle } = this.echart || {}
      colTitle && (colTitle = ["$", "O"].concat(colTitle))
      data = data.map((i, k) => {
        return [rowTitle[k].type, '+'].concat(i)
      })
      return [].concat([colTitle], data)
    }
  },
  methods: {
    changeChart (type, index = null) {
      const oldData = this.echart
      const { rowTitle } = oldData
      if (index !== null) {
        rowTitle[index - 1].type = type
      } else
        rowTitle.forEach((i, k) => {
          rowTitle[k].type = type
        })
      this.echart = oldData
    },
    echartData () {
      let data = {}
      const { rowTitle, colTitle, data: allData } = this.echart

      data.legend = { data: [] }
      data.xAxis = { data: colTitle }
      data.yAxis = { type: 'value' }
      data.series = rowTitle.map((i, k) => {
        data.legend.data.push(i.name)
        return {
          name: i,
          type: i.type,
          data: allData[k],
          itemStyle: {
            normal: {
              label: {
                show: true
              }
            }
          }
        }
      })
      return data
    },
    init () {
      this.echartNode = echarts.init(this.$refs.echart)
      this.drawChart()
    },
    drawChart () {
      let chartOption = this.echartData()
      // let chartOption = this.setOptionFn()
      this.echartNode.setOption(chartOption, true)
      this.echartNode.resize()
    },
    setOptionFn () {
      const { chartType, legend, data, color } = this.data.obj
      const fontColor = this.fontColor || "#000"
      this.chartType = chartType
      let chartOption = {
        legend: { data: legend, y: 'bottom', textStyle: { color: fontColor } },
        series: data,
        color
      }
      // 配置非饼图得数据
      if (!this.isPie()) {
        let { xAxis, data } = this.data.obj
        chartOption.tooltip = { trigger: 'axis' }
        chartOption.xAxis = {
          data: xAxis,
          axisLabel: {
            color: fontColor
          }
        }
        chartOption.yAxis = {
          type: 'value',
          axisLabel: {
            color: fontColor
          }
        }
        chartOption.series = this._chartData(data)
      } else {
        const { data } = this.data.obj
        chartOption.tooltip = {
          trigger: 'item',
          formatter: '{a} <br/>{b}: {c} ({d}%)'
        }
        chartOption.series = this._chartData(data)
      }

      return chartOption
    },

    _chartData (chartData) {
      const { color } = this.data.obj
      let newData = []
      let that = this
      chartData.forEach((item, index) => {
        let _itemObj = {}
        if (!that.isPie()) {
          let { data, name, type, itemStyle } = item
          _itemObj = { data, name, itemStyle, type }
          _itemObj.itemStyle.normal.color = color[index]
          // if (type === that.CHART_TYPE.AREA) {
          //   _itemObj.type = that.CHART_TYPE.LINE
          //   _itemObj.areaStyle = {}
          // }
        } else {
          let { data, name, label, type } = item
          _itemObj = { data, name, label, type }
          // if (type === that.CHART_TYPE.PIE) {
          //   _itemObj.radius = '55%'
          // } else if (type === that.CHART_TYPE.PIE3D) {
          //   _itemObj.radius = [30, 120]
          //   _itemObj.roseType = 'area'
          // }
        }
        newData.push(_itemObj)
      })
      return newData
    },
    isPie () {
      let boolean = false
      // if (this.chartType === this.CHART_TYPE.LINE ||
      //   this.chartType === this.CHART_TYPE.AREA ||
      //   this.chartType === this.CHART_TYPE.BAR
      // ) {
      //   boolean = false
      // } else {
      //   boolean = true
      // }
      return boolean
    }
  },
  created () {
  },
  mounted () {
    this.init()
  }
}
</script>
<style scoped>
.wrapper {
  padding: 0px;
  margin: 0px;
}
</style>
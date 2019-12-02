<template>
  <div>
      <div class="address-box" v-for="(item, index) in addressItem" :key="index">
        <div class="address-header">
          <span>{{item.addressee}}</span>
          <div class="address-action">
            <span @click="edit(index)">
              <Icon type="edit"></Icon> 修改
            </span>
            <span @click="del(index)">
              <Icon type="trash-a"></Icon> 删除
            </span>
          </div>
        </div>
        <div class="address-content">
          <p><span class="address-content-title"> 收 货 人 :</span> {{item.addressee}}</p>
          <p><span class="address-content-title">收货地区:</span> {{item.province}} {{item.city}} {{item.area}}</p>
          <p><span class="address-content-title">收货地址:</span> {{item.address}}</p>
          <p><span class="address-content-title">邮政编码:</span> {{item.zipCode}}</p>
        </div>
      </div>
    <Modal v-model="modal" width="530">
      <p slot="header">
        <Icon type="edit"></Icon>
        <span>修改地址</span>
      </p>
      <div class="update-box">
        <Form ref="formData" :model="formData" label-position="left" :label-width="100" :rules="ruleInline">
          <FormItem label="收件人" prop="addressee">
            <i-input v-model="formData.addressee" size="large"></i-input>
          </FormItem>
          <FormItem label="收件地区" prop="address">
            <Distpicker :province="formData.province" :city="formData.city" :area="formData.area" @province="getProvince"
              @city="getCity" @area="getArea"></Distpicker>
          </FormItem>
          <FormItem label="收件地址" prop="address">
            <i-input v-model="formData.address" size="large"></i-input>
          </FormItem>
          <FormItem label="手机号码" prop="telethone">
            <i-input v-model="formData.telethone" size="large"></i-input>
          </FormItem>
          <FormItem label="邮政编码" prop="zipCode">
            <i-input v-model="formData.zipCode" size="large"></i-input>
          </FormItem>
        </Form>
      </div>
      <div slot="footer">
        <Button type="primary" size="large" long @click="editAction">修改</Button>
      </div>
    </Modal>
  </div>
</template>

<script>
  import {mapState,mapActions} from 'vuex';
  import Distpicker from 'v-distpicker';
  export default {
    name: 'MyAddress',
    data() {
      return {
        modal: false,
        index: 0,
        addressItem:null,
        formData: {
          addressee: '',
          address: '',
          telethone: '',
          zipCode: '',
          province: '广东省',
          city: '广州市',
          area: '天河区',
          id: ''
        },
        ruleInline: {
          addressee: [{
            required: true,
            message: '请输入收件人姓名',
            trigger: 'blur'
          }],
          address: [{
            required: true,
            message: '请输入收件地址',
            trigger: 'blur'
          }],
          zipCode: [{
              required: true,
              message: '请输入邮政编码',
              trigger: 'blur'
            },
            {
              type: 'string',
              min: 6,
              max: 6,
              message: '邮政编码必须为六位',
              trigger: 'blur'
            }
          ],
          telethone: [{
              required: true,
              message: '请输入联系人电话',
              trigger: 'blur'
            },
            {
              type: 'string',
              pattern: /^1[3|4|5|7|8][0-9]{9}$/,
              message: '手机号格式出错',
              trigger: 'blur'
            }
          ]
        }
      };
    },
    created() { //钩子函数触发获取分类的http请求(在创建vue对象时，在html渲染之前就触发)
      this.getList();
    },
    computed: {
       ...mapState(['userInfo'])
     },
    methods: {
      /* ...mapActions(['GetAddress']), */
      getProvince(data) {
        this.formData.province = data.value;
      },
      getCity(data) {
        this.formData.city = data.value;
      },
      getArea(data) {
        this.formData.area = data.value;
      },
      getList(){
         this.$store.dispatch('GetAddress', this.userInfo.idCard).then(response => {
            this.addressItem =response;
          }).catch(() => {
              this.$Message.error("数据异常，请稍后再试");
              this.edit(this.index);
           });
      },
      edit(index) {
        this.modal = true;
        this.index = index;
        this.formData.province = this.addressItem[index].province;
        this.formData.city = this.addressItem[index].city;
        this.formData.area = this.addressItem[index].area;
        this.formData.address = this.addressItem[index].address;
        this.formData.addressee = this.addressItem[index].addressee;
        this.formData.telethone = this.addressItem[index].telethone;
        this.formData.zipCode = this.addressItem[index].zipCode.toString().trim();
        this.formData.id = this.addressItem[index].id;
      },
      editAction() {
        this.modal = false;
        this.$refs.formData.validate((valid) => {
          if (valid && (this.formData.province + this.formData.city + this.formData.area) != "省市区") {
            this.$store.dispatch('UpdateAddress', this.formData).then(() => {
              this.$Message.success('修改成功');
              //修改成功之后刷新页面数据
              this.getList();//拉取后台数据
            }).catch(() => {
              this.$Message.error("操作失败");
              this.edit(this.index);
            });
          } else {
            this.$Message.error("请输入完整的参数，参数验证不合法");
            console.log("参数验证不合法");
            this.edit(this.index);
            return false;
          }
        });
      },
      del(index) {
        this.$Modal.confirm({
          title: '信息提醒',
          content: '你确定删除这个收货地址',
          onOk: () => {
            this.$store.dispatch('DeleteAddress', this.addressItem[index]).then(() => {
              this.$Message.success("删除成功");
              //删除成功之后刷新页面数据
               this.getList();//拉取后台数据
            }).catch(() => {
              this.$Message.error("操作失败");
            });
          },
          onCancel: () => {
            this.$Message.info('取消删除');
          }
        });
      }
    },
    components: {
      Distpicker
    }
  };
</script>

<style scoped>
  .address-box {
    padding: 15px;
    margin: 15px;
    border-radius: 5px;
    box-shadow: 0px 0px 5px #ccc;
  }

  .address-header {
    height: 35px;
    display: flex;
    justify-content: space-between;
    color: #232323;
    font-size: 18px;
  }

  .address-content {
    font-size: 14px;
  }

  .address-content-title {
    color: #999;
  }

  .address-action span {
    margin-left: 15px;
    font-size: 14px;
    color: #2d8cf0;
    cursor: pointer;
  }
</style>

<template>
  <div>
    <div class="add-container">
      <div class="add-title">
        <h1>添加收货地址</h1>
      </div>
      <div class="add-box">
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
      <div class="add-submit">
        <!--图标加文字按钮-->
        <Button type="primary" long @click="addAddress()">添加地址</Button>
      </div>
    </div>
  </div>
</template>

<script>
  import Distpicker from 'v-distpicker';
  export default {
    addressee: 'AddAddress',
    data() {
      return {
        formData: {
          addressee: '',
          address: '',
          telethone: '',
          zipCode: '',
          province: '广东省',
          city: '广州市',
          area: '天河区'
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
    methods: {
      getProvince(data) {
        this.formData.province = data.value;
      },
      getCity(data) {
        this.formData.city = data.value;
      },
      getArea(data) {
        this.formData.area = data.value;
      },
      addAddress() {
        this.$refs.formData.validate((valid) => {
          if (valid) {
            this.$store.dispatch('AddAddress', this.formData).then(() => {
               this.$Message.success("新增收货地址成功");
               //清空数据
               this.formData.addresse= null;
               this.formData.address= null;
               this.formData.telethone= null;
               this.formData.zipCode= null;
            }).catch(() => {
              console.log('地址失败');
               this.$Message.error("新增收货地址失败，请稍后再试");
            });
          } else {
            console.log("参数验证不合法");
            return false;
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
  .add-container {
    margin: 15px auto;
    width: 60%;
    min-width: 600px;
  }

  .add-title {
    margin-bottom: 15px;
    text-align: center;
  }

  .add-submit {
    display: flex;
    justify-content: center;
  }
</style>

import * as style from "@/components/page/MainPage/MainComponents/style/GithubStatus";
import TextBox from "@/components/common/TextBox/TextBox";
interface Props {
  statusData: {
    commitCnt: number;
  };
  commitData: {
    relayCnt: number;
    todayCnt: number;
  };
}
const GithubStatus = (props: Props) => {
  const { statusData } = props;
  const { commitData } = props;
  return (
    <style.StatusContainer>
      <style.StatusBar>
        <style.StatusBox1>
          <style.MinusIcon />
        </style.StatusBox1>
        <style.StatusBox2>
          <style.SquareIcon />
        </style.StatusBox2>
        <style.StatusBox3>
          <style.Sect01>
            <style.LineBox>
              <style.Line01 />
              <style.Line02 />
            </style.LineBox>
          </style.Sect01>
        </style.StatusBox3>
      </style.StatusBar>
      <style.Margin />
      <TextBox size={25} content={`Total Commits : ${statusData.commitCnt}`} />
      <style.Margin />
      <TextBox size={25} content={`Today Commits : ${commitData.todayCnt}`} />
      <style.Margin />
      <TextBox
        size={23}
        content={`Consecutive Commits : ${commitData.relayCnt}`}
      />
    </style.StatusContainer>
  );
};
export default GithubStatus;

import * as style from "@/components/page/MainPage/MainComponents/style/GithubStatus";
import TextBox from "@/components/common/TextBox/TextBox";
interface Props {
  statusData: {
    commitCnt: number;
    starCnt: number;
    forkCnt: number;
  };
}
const GithubStatus = (props: Props) => {
  const { statusData } = props;
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
      <TextBox size={25} content={`Total Stars : ${statusData.starCnt}`} />
      <style.Margin />
      <TextBox size={25} content={`Total Forks : ${statusData.forkCnt}`} />
    </style.StatusContainer>
  );
};
export default GithubStatus;

import * as style from "@/components/page/MainPage/MainComponents/style/ExpProgress";
interface Props {
  avatarData: {
    level: number;
    exp: number;
    prevExp: number;
    nextExp: number;
    outfits: {
      head: string;
      effect: string;
      body: string;
    };
  };
}
const ExpProgress = (props: Props) => {
  const { avatarData } = props;

  return (
    <>
      <style.FullDiv>
        <style.RangeDiv
          prevExp={avatarData.prevExp}
          exp={avatarData.exp}
          nextExp={avatarData.nextExp}
        />
      </style.FullDiv>
    </>
  );
};
export default ExpProgress;

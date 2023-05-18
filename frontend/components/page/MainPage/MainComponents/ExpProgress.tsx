import * as style from "@/components/page/MainPage/MainComponents/style/ExpProgress";
interface Props {
  avatarData: {
    level: number;
    exp: number;
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
  console.log(avatarData.exp);
  console.log(avatarData.nextExp);

  return (
    <>
      <style.FullDiv>
        <style.RangeDiv
          level={avatarData.level}
          exp={avatarData.exp}
          nextExp={avatarData.nextExp}
        />
      </style.FullDiv>
    </>
  );
};
export default ExpProgress;

import React, {Component} from 'react';
import constImages from '../img/index'
import RateModal from "./RateModal";

class MovieItem extends Component {
  render() {
    const {item} = this.props;
    const icons = constImages.genre;
    const icon = icons[item.genre] ? icons[item.genre] : icons.default;

    return (
        <tr>
          <td>{item.title}</td>
          <td>{item.productionDate}</td>
          <td><img alt={item.genre} className="image" src={icon}/></td>
          <td>{item.score ? `${item.score}(${item.numberOfVoters})`
              : 'No score'}</td>
          <td><RateModal movieTitle={item.title}
                         movieId={item.id}/></td>
        </tr>
    );
  }
}

export default MovieItem;

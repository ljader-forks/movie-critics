import React, {Component} from 'react';
import constImages from '../img/index'

class MovieItem extends Component {
  render() {
    const {item} = this.props;
    const icons = constImages.genre;
    const icon = icons[item.genre] ? icons[item.genre] : icons.default;

    return (
        <tr>
          <td>{item.title}</td>
          <td>{item.productionDate}</td>
          <td><img className="image" src={icon}/></td>
          <td>{item.score ? item.score : 'No score'}</td>
        </tr>
    );
  }
}

export default MovieItem;
